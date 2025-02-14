package dev.earlspilner.books.security;

import dev.earlspilner.books.dto.UserDto;
import dev.earlspilner.books.feign.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Nikita Zhelezko
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userClient.getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.withUsername(username)
                .password(user.password())
                .authorities(user.roles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
