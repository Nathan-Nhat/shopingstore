package com.ttnhat.shop.Sercurity.JWT.JWTUtils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtilsImplement implements IJwtUtils {
    Logger logger = LoggerFactory.getLogger(JwtUtilsImplement.class);
    private static final String SECRET_KEY = "SECRET_KEY";

    @Override
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String roles = String.join(",",userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority().toString()).collect(Collectors.toList()));
        claims.put("roles", roles);
        logger.info(roles);
        return createToken(claims, userDetails.getUsername());
    }

    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public String extractRoles(String token) {
        return extractAllClaims(token).get("roles").toString();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return ((username.equals(userDetails.getUsername())) && (!isTokenExpired(token)));
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 5))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private boolean isTokenExpired(String token){
        return extractExperationToken(token).before(new Date(System.currentTimeMillis()));
    }

    private Claims extractAllClaims(String token) {
        Claims claims = null;
        claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims;
    }

    private Date extractExperationToken(String token) {
        return extractAllClaims(token).getExpiration();
    }
}
