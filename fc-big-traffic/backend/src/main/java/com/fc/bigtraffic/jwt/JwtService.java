package com.fc.bigtraffic.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {

    private String secretKey = "secret";
    private long expirationTime = 360000;

    public String createToken(String userId) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expirationTime);

        return JWT.create()
                .withSubject(userId)
                .withClaim("userId", userId)
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
        return JWT.decode(token).getClaim("userId").asString();
    }

}
