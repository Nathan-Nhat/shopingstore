package com.ttnhat.shop.Sercurity.JWT.JWTRespone;

public class JwtObject {

    private String jwtToken;

    public JwtObject(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
