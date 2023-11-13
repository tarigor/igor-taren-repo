package com.senla.hotelsecurity.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@Slf4j
public class JwtTokenService {

    private final Algorithm hmac512;
    private final JWTVerifier verifier;
    @Value("${jwt.token.validate.time.minutes}")
    private int JWT_TOKEN_VALIDITY;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(Duration.ofMinutes(JWT_TOKEN_VALIDITY).toMillis()))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            log.warn("access denied for user -> {} - token: {} is invalid", verifier.verify(token).getSubject(), verificationEx.getMessage());
            return null;
        }
    }

    public String getExpirationTime(final String token) {
        return verifier.verify(token).getExpiresAt().toString();
    }
}

