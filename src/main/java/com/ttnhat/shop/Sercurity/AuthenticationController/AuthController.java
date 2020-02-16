package com.ttnhat.shop.Sercurity.AuthenticationController;

import com.ttnhat.shop.ExceptionHandler.ErrorType;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Sercurity.JWT.JWTRespone.JwtObject;
import com.ttnhat.shop.Sercurity.JWT.JWTUtils.IJwtUtils;
import com.ttnhat.shop.Sercurity.JWT.JWTUtils.JwtUtilsImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/authenticate")
    public ResponseEntity<?> getJwtToken(@RequestBody UsersEntity usersEntity){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                usersEntity.getUsername(), usersEntity.getPassword()
        );
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e)
        {
            throw new UsernameNotFoundException(ErrorType.USER_NOTFOUND.toString());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(usersEntity.getUsername());
        final IJwtUtils  jwtUtils = new JwtUtilsImplement();
        final JwtObject jwtObject = new JwtObject(jwtUtils.generateJwtToken(userDetails));
        return ResponseEntity.ok(jwtObject);
    }
 }
