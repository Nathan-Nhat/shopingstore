package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Sercurity.JWT.JWTRespone.JwtObject;
import com.ttnhat.shop.Sercurity.JWT.JWTUtils.JwtUtilsImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/1")
    public ResponseEntity<?> validateToken(){
        MapperEntity mapperEntity = this.restTemplate.getForObject("http://echo.jsontest.com/key/value/one/two", MapperEntity.class);
        return ResponseEntity.ok(mapperEntity);
    }
}
