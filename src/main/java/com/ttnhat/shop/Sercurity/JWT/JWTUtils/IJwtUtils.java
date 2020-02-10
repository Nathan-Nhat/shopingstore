package com.ttnhat.shop.Sercurity.JWT.JWTUtils;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IJwtUtils {

    String generateJwtToken(UserDetails userDetails);
    String extractUsername(String token);
    String extractRoles(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
