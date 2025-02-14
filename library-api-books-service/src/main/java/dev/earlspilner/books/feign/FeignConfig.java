package dev.earlspilner.books.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikita Zhelezko
 */
@Configuration
public class FeignConfig {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("Feign-ID", secretKey);
    }

}
