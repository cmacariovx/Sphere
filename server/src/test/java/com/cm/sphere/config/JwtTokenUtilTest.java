package com.cm.sphere.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.cm.sphere.AwsSecretManager;
import com.cm.sphere.AwsSecretManager.SecretKeys;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtilTest {
    private final SecretKeys secretTokenKeys;
    private final String refreshSecretKey;
    private final String accessSecretKey;
    private final long refreshTokenExpiration = Duration.ofDays(14).getSeconds();
    private final long accessTokenExpiration = Duration.ofMinutes(30).getSeconds();
    private static final long expiredTokenExpiration = -1;

    public JwtTokenUtilTest() {
        this.secretTokenKeys = AwsSecretManager.getTokenSecretKeys();
        this.refreshSecretKey = this.secretTokenKeys.getRefreshTokenSecretKey();
        this.accessSecretKey = this.secretTokenKeys.getAccessTokenSecretKey();
    }

    public String generateValidTestToken(String userId, int tokenType) {
        final String tokenKey = (tokenType == 0 ? this.refreshSecretKey : this.accessSecretKey);
        final long tokenExpiration = (tokenType == 0 ? this.refreshTokenExpiration : this.accessTokenExpiration);
        final Map<String, Object> claims = new HashMap<>();

        claims.put("token_type", tokenType);

        return createTestToken(claims, userId, tokenKey, tokenExpiration);
    }

    public String generateExpiredTestToken(String userId, int tokenType) {
        final String tokenKey = (tokenType == 0 ? this.refreshSecretKey : this.accessSecretKey);
        final Map<String, Object> claims = new HashMap<>();

        claims.put("token_type", tokenType);

        return createTestToken(claims, userId, tokenKey, expiredTokenExpiration);
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
