package com.cm.sphere.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.cm.sphere.AwsSecretManager;
import com.cm.sphere.AwsSecretManager.SecretKeys;
import com.cm.sphere.exception.ExpiredAccessTokenException;
import com.cm.sphere.exception.MissingRefreshTokenException;
import com.cm.sphere.exception.TamperedJwtException;
import com.cm.sphere.model.security.AuthUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class JwtTokenUtil {
    private final SecretKeys secretTokenKeys;
    private final String refreshSecretKey;
    private final String accessSecretKey;
    private final long refreshTokenExpiration = Duration.ofDays(14).getSeconds();
    private final long accessTokenExpiration = Duration.ofMinutes(30).getSeconds();
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public JwtTokenUtil() {
        this.secretTokenKeys = AwsSecretManager.getTokenSecretKeys();
        this.refreshSecretKey = this.secretTokenKeys.getRefreshTokenSecretKey();
        this.accessSecretKey = this.secretTokenKeys.getAccessTokenSecretKey();
    }

    public void validateToken(String token, AuthUserDetails userDetails, int tokenType) {
        final String id = getIdFromToken(token, tokenType);
        if (!id.equals(userDetails.getId())) throw new TamperedJwtException();
        if (isTokenExpired(token, tokenType)) throw new ExpiredAccessTokenException();
    }

    public String getIdFromToken(String token, int tokenType) {
        return getClaimFromToken(token, Claims::getSubject, tokenType);
    }

    public Boolean isTokenExpired(String token, int tokenType) {
        return extractExpiration(token, tokenType).before(new Date());
    }

    public Date extractExpiration(String token, int tokenType) {
        return extractAllClaims(token, tokenType).getExpiration();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver, int tokenType) {
        final Claims claims = extractAllClaims(token, tokenType);
        return claimsResolver.apply(claims);
    }

    public <T> T getClaimFromTokenWithoutValidation(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaimsWithoutValidation(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, int tokenType) {
        final String secretKey = (tokenType == 0 ? this.refreshSecretKey : this.accessSecretKey);
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception err) {
            logger.error("JwtTokenUtil - Extract all claims: " + err);
            throw new MissingRefreshTokenException();
        }
    }

    public Claims extractAllClaimsWithoutValidation(String token) {
        final String[] chunks = token.split("\\.");

        final Base64.Decoder decoder = Base64.getDecoder();
        final String payload = new String(decoder.decode(chunks[1]));

        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final Map<String, Object> claimsMap = objectMapper.readValue(payload, new TypeReference<Map<String, Object>>(){});
            return Jwts.claims(claimsMap);
        }
        catch (final IOException e) {
            throw new RuntimeException("Failed to decode and map payload", e);
        }
    }

    public String generateToken(String userId, int tokenType) {
        final String tokenKey = (tokenType == 0 ? this.refreshSecretKey : this.accessSecretKey);
        final long tokenExpiration = (tokenType == 0 ? this.refreshTokenExpiration : this.accessTokenExpiration);
        final Map<String, Object> claims = new HashMap<>();

        claims.put("token_type", tokenType);

        return createToken(claims, userId, tokenKey, tokenExpiration);
    }

    private String createToken(Map<String, Object> claims, String subject, String secretKey, long tokenExpiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
