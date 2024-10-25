package br.com.brendosp.authapi.useCases;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUseCase {

    @Value("${security.jwt.secret:36cf718d7921aa0f82318cb7339c474b}")
    private String secret;
    @Value("${security.jwt.expiration:3600000}")
    private Long expiration;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuer("auth-api")
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSecretKey())
            .compact();
    }

    public Boolean isValidToken(String token) {
        try {
            return getClaimFromToken(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
