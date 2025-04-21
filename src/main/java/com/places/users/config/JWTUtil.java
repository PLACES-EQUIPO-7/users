package com.places.users.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.places.users.service.UserDetailsEntity;
import com.places.users.utils.RSAKeyLoader;
import com.places.users.utils.mappers.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private final Algorithm ALGORITHM;

    public JWTUtil() throws Exception {
        RSAPrivateKey PRIVATE_KEY = RSAKeyLoader.loadPrivateKey("./private_key.pem");
        RSAPublicKey PUBLIC_KEY = RSAKeyLoader.loadPublicKey("./public_key.pem");
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
