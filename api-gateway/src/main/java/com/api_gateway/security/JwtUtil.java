package com.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtUtil {

    private static final String SECRET =
            "my-super-secret-key-my-super-secret-key-123456";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8));

    public static Claims validateToken(String token){

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}