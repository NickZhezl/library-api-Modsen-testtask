package dev.earlspilner.library.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Nikita Zhelezko
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf(AbstractHttpConfigurer::disable);

        // No session will be created or used by spring security
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Entry points
        http.authorizeHttpRequests((authz) -> authz
                // Allow requests from Feign Client
                .requestMatchers(request -> {
                    String feignId = request.getHeader("Feign-ID");
                    return feignId != null && feignId.equals(jwtTokenProvider.getSecretKey());
                }).permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api-docs/openapi.yml").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                // Disallow everything else...
                .anyRequest().authenticated());

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling((exception) -> exception
                .accessDeniedPage("/users"));

        // Apply JWT
        http.with(new JwtTokenFilterConfigurer(jwtTokenProvider), Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return web -> web.ignoring().requestMatchers("/v2/api-docs")//
                .requestMatchers("/swagger-resources/**")//
                .requestMatchers("/swagger-ui.html")//
                .requestMatchers("/configuration/**")//
                .requestMatchers("/webjars/**")//
                .requestMatchers("/public");
    }

}
