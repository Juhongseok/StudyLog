package com.fc.bigtraffic.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fc.bigtraffic.entity.JwtBlacklist;
import com.fc.bigtraffic.repository.JwtBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtService {

    private String secretKey = "secret";
    private long expirationTime = 360000;

    private final JwtBlacklistRepository jwtBlacklistRepository;

    public String createToken(String email) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expirationTime);

        return JWT.create()
                .withSubject(email)
                .withClaim("email", email)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512(secretKey));
    }

    public boolean validate(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);

            return true;
        } catch (TokenExpiredException e) {
            return false;
        }
    }

    public String getUserId(String token) {
        return JWT.decode(token).getClaim("email").asString();
    }

    public void blacklistToken(String token) {
        JwtBlacklist jwtBlacklist = new JwtBlacklist(token, LocalDateTime.now(), null);
        jwtBlacklistRepository.save(jwtBlacklist);
    }

    public void blacklistTokenAll(String token) {
        String email = extractEmail(token);
        JwtBlacklist jwtBlacklist = new JwtBlacklist(token, LocalDateTime.now(), email);
        jwtBlacklistRepository.save(jwtBlacklist);
    }

    public boolean isNotBlacklisted(String token) {
        String email = extractEmail(token);
        Optional<JwtBlacklist> blacklisted = jwtBlacklistRepository.findTopByEmailOrderByExpirationTimeDesc(email);

        if (blacklisted.isPresent()) {
            JwtBlacklist blacklistedToken = blacklisted.get();
            LocalDateTime expirationTime = getExpirationTimeFrom(token);

            return !blacklistedToken.isExpired(expirationTime);
        }

        return true;
    }

    private String extractEmail(String token) {
        return JWT.decode(token).getClaim("email").asString();
    }

    private LocalDateTime getExpirationTimeFrom(String token) {
        Date expiresAt = JWT.require(Algorithm.HMAC512(secretKey))
                .build()
                .verify(token)
                .getExpiresAt();

        return expiresAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
