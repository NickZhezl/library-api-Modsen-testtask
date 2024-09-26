package dev.earlspilner.users.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Nikita Zhelezko
 */
public enum UserRole implements GrantedAuthority {
    ROLE_VISITOR, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
