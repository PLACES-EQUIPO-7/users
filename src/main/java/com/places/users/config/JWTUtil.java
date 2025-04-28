package com.places.users.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.places.users.service.UserDetailsEntity;
import com.places.users.utils.RSAKeyLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class JWTUtil {

    private final Algorithm ALGORITHM;

    public JWTUtil(@Value( "${jwt.private-key-path}") final String privateKeyPath,
                   @Value( "${jwt.public-key-path}") final String publicKeyPath) throws Exception {
        RSAPrivateKey PRIVATE_KEY = RSAKeyLoader.loadPrivateKey(privateKeyPath);
        RSAPublicKey PUBLIC_KEY = RSAKeyLoader.loadPublicKey(publicKeyPath);
        ALGORITHM = Algorithm.RSA256(PUBLIC_KEY, PRIVATE_KEY);
    }

    public String create(Authentication authentication) {

        UserDetailsEntity userDetails = (UserDetailsEntity) authentication.getPrincipal();

        return JWT.create()
                .withSubject(userDetails.getUserId())
                .withIssuer("places-users")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(10, ChronoUnit.HOURS))
                .withClaim("role", userDetails.getRole().getValue())
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt) {

        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            log.error("Token inválido: {}", e.getMessage());
            return false;
        }

    }

    public Object getTokenDetails(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getPayload();
    }
}
