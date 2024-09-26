package dev.earlspilner.auth.rest.controller;

import dev.earlspilner.auth.dto.AuthDto;
import dev.earlspilner.auth.dto.Tokens;
import org.springframework.http.ResponseEntity;

/**
 * @author Nikita Zhelezko
 */
public interface AuthenticationServerApi {
    ResponseEntity<Tokens> login(AuthDto authDto);
    ResponseEntity<Tokens> refresh(String refreshToken);
}
