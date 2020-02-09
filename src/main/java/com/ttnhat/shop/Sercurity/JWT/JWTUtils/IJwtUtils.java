package com.ttnhat.shop.Sercurity.JWT.JWTUtils;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IJwtUtils {

    String generateJwtToken();
    String extractUsername(String token);
    List<String> extractRoles(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
