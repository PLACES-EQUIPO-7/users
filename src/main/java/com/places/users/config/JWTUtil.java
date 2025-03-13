package com.places.users.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.places.users.service.UserDetailsEntity;
import com.places.users.utils.mappers.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private static final String SECRET_KEY = "places";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(Authentication authentication) {

        UserDetailsEntity userDetails = (UserDetailsEntity) authentication.getPrincipal();

        List<Map<String, String>> relatedPlaces = UserMapper.buildPlaceUserRelationsClaim(userDetails.getPlacesInfo().getRelatedPlaces());

        UserMapper.buildPlacesInfoFromClaim(relatedPlaces);

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("test")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
                .withClaim("related_places", relatedPlaces)
                .withClaim("user_id", userDetails.getUserId())
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
