package dev.earlspilner.auth.security;

import dev.earlspilner.auth.dto.Tokens;
import dev.earlspilner.auth.dto.UserRole;
import dev.earlspilner.auth.rest.advice.custom.CustomJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * @author Nikita Zhelezko
 */
@Service
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private Long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshExpiration;

    private Key key;

    @Autowired
    private CustomUserDetails myUserDetails;

    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey.getBytes()));
    }

    public String createToken(String username, List<UserRole> userRoles, Long tokenExpiration) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenExpiration);

        JwtBuilder builder = Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key);

        builder.claim("auth", userRoles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .filter(Objects::nonNull)
                .collect(toList()));

        return builder.compact();
    }

    public Tokens createTokens(String username, List<UserRole> userRoles) {
        String accessToken = createToken(username, userRoles, accessExpiration);
        String refreshToken = createToken(username, List.of(), refreshExpiration);
        return new Tokens(accessToken, refreshToken);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomJwtException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }

}
