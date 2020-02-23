package com.ttnhat.shop.Sercurity.JWT.JWTRespone;

public class JwtObject {

    private String jwtToken;
    private String username;
    private String roles;

    public JwtObject(String jwtToken, String username, String roles)
    {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
