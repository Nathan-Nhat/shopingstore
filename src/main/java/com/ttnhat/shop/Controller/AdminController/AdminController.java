package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Sercurity.JWT.JWTRespone.JwtObject;
import com.ttnhat.shop.Sercurity.JWT.JWTUtils.JwtUtilsImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping(value = "/test/{token}")
    public ResponseEntity<?> validateToken(@PathVariable(name = "token") String token){
        JwtUtilsImplement jwtUtilsImplement = new JwtUtilsImplement();
        String username = jwtUtilsImplement.extractUsername(token);
        JwtObject jwtObject = new JwtObject(username);
        return ResponseEntity.ok(jwtObject);
    }
}
