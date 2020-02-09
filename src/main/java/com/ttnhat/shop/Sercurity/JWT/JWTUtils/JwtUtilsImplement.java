package com.ttnhat.shop.Sercurity.JWT.JWTUtils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class JwtUtilsImplement implements IJwtUtils {

    private UserDetails userDetails;
    public JwtUtilsImplement(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String generateJwtToken() {
        return "123";
    }

    @Override
    public String extractUsername(String token) {
        return null;
    }

    @Override
    public List<String> extractRoles(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        return false;
    }
}
