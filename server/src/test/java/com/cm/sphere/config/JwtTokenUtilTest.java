package com.cm.sphere.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtilTest {
    private static final String testSecretKey = "your_test_secret_key_test_test_test_test";
    private static final long expiredTokenExpiration = -1;

    public String generateValidTestToken(String userId, int tokenType) {
        final long tokenExpiration = Duration.ofMinutes(30).getSeconds();
        final Map<String, Object> claims = new HashMap<>();

        claims.put("token_type", tokenType);

        return createTestToken(claims, userId, testSecretKey, tokenExpiration);
    }

    public String generateExpiredTestToken(String userId, int tokenType) {
        final Map<String, Object> claims = new HashMap<>();

        claims.put("token_type", tokenType);

        return createTestToken(claims, userId, testSecretKey, expiredTokenExpiration);
    }

    public String generateTamperedTestToken() {
        return "tamperedToken";
    }

    private String createTestToken(Map<String, Object> claims, String subject, String secretKey, long tokenExpiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
