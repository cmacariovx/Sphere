package com.cm.sphere.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import com.cm.sphere.AwsSecretManager;
import com.cm.sphere.AwsSecretManager.SecretKeys;
import com.cm.sphere.exception.ExpiredAccessTokenException;
import com.cm.sphere.exception.TamperedJwtException;
import com.cm.sphere.model.Security.AuthUserDetails;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtTokenUtil {
    private final String refreshSecretKey;
    private final String accessSecretKey;
    private final long refreshTokenExpiration = Duration.ofDays(14).getSeconds();
    private final long accessTokenExpiration = Duration.ofMinutes(30).getSeconds();
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public JwtTokenUtil() {
        SecretKeys secretTokenKeys = AwsSecretManager.getTokenSecretKeys();
        this.refreshSecretKey = secretTokenKeys.getRefreshTokenSecretKey();
        this.accessSecretKey = secretTokenKeys.getAccessTokenSecretKey();
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
            logger.error("JwtTokenUtil - Extract all claims: " + err.getMessage());
            throw new RuntimeException("Could not parse JWT token.", err);
        }
    }

    private Claims extractAllClaimsWithoutValidation(String token) {
        try {
            return Jwts.parserBuilder()
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        }
        catch (Exception err) {
            logger.error("JwtTokenUtil - Extract all claims w/o validation: " + err.getMessage());
            throw new RuntimeException("Could not parse JWT token.", err);
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
