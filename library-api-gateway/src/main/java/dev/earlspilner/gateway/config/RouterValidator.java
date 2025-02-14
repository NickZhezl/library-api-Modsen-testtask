package dev.earlspilner.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Nikita Zhelezko
 */
@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/login",
            "/api/auth/refresh",
            "/api/users"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
