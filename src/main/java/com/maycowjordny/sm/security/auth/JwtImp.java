package com.maycowjordny.sm.security.auth;

import com.auth0.jwt.algorithms.Algorithm;
import com.maycowjordny.sm.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtImp implements JwtService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Override
    public String encode(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("auth0")
                .withExpiresAt(Instant.now().plus(Duration.ofDays(365)))
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .sign(algorithm);
    }

    @Override
    public String decode(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.replace("Bearer ","");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        return verifier.verify(token).getSubject();
    }
}
