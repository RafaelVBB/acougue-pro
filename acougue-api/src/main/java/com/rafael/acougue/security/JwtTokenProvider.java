package com.rafael.acougue.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String CLAIM_ROLES = "roles";
    private static final String CLAIM_TYPE = "type";
    private static final String ACCESS_TYPE = "ACCESS";
    private static final String REFRESH_TYPE = "REFRESH";

    private final String secret;
    private final long accessTokenExpirationMs;
    private final long refreshTokenExpirationMs;
    private Key signingKey;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.access-token-expiration:3600000}") long accessTokenExpirationMs,
                            @Value("${jwt.refresh-token-expiration:604800000}") long refreshTokenExpirationMs) {
        this.secret = secret;
        this.accessTokenExpirationMs = accessTokenExpirationMs;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    }

    @PostConstruct
    void init() {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(userDetails, accessTokenExpirationMs, ACCESS_TYPE);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails, refreshTokenExpirationMs, REFRESH_TYPE);
    }

    private String buildToken(UserDetails userDetails, long expirationMs, String type) {
        Instant now = Instant.now();
        Instant expiration = now.plusMillis(expirationMs);
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .claim(CLAIM_ROLES, extractRoles(userDetails.getAuthorities()))
            .claim(CLAIM_TYPE, type)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiration))
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isAccessToken(String token) {
        return ACCESS_TYPE.equalsIgnoreCase(getTokenType(token));
    }

    public boolean isRefreshToken(String token) {
        return REFRESH_TYPE.equalsIgnoreCase(getTokenType(token));
    }

    public String getUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    public Instant getExpirationInstant(long expirationMs) {
        return Instant.now().plusMillis(expirationMs);
    }

    public long getAccessTokenExpirationMs() {
        return accessTokenExpirationMs;
    }

    public long getRefreshTokenExpirationMs() {
        return refreshTokenExpirationMs;
    }

    private List<String> extractRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).toList();
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
    }

    private String getTokenType(String token) {
        return parseClaims(token).getBody().get(CLAIM_TYPE, String.class);
    }
}
