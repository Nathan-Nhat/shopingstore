package com.ttnhat.shop.Sercurity.JWT.JWTFilter;

import com.ttnhat.shop.ExceptionHandler.ConvertObjectToJson;
import com.ttnhat.shop.ExceptionHandler.DebugLog;
import com.ttnhat.shop.ExceptionHandler.ErrorType;
import com.ttnhat.shop.ExceptionHandler.ExceptionMessageObject;
import com.ttnhat.shop.Sercurity.JWT.JWTUtils.JwtUtilsImplement;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtUtilsImplement jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        final String header= request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        try {
            if (!request.getRequestURI().equals("/authenticate")) {
                if (header != null && header.startsWith("Bearer ")) {
                    jwt = header.substring(7);
                    username = jwtUtil.extractUsername(jwt);
                }
                if (username != null) {
                    UserDetails userDetail = userDetailService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(jwt, userDetail)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetail, null, userDetail.getAuthorities());
                        logger.info(userDetail.getAuthorities().toString());
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
            catch (ExpiredJwtException e)
            {
                Date date = new Date();
                String path = request.getServletPath();
                ExceptionMessageObject exceptionMessageObject = new ExceptionMessageObject(
                        date, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        ErrorType.EXPIRED_JWT_TOKEN.toString(), e.getMessage(), path, new DebugLog(e).getLog()
                );
                ConvertObjectToJson convertObjectToJson = new ConvertObjectToJson(exceptionMessageObject);
                response.setContentType("application/json");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getWriter().write(convertObjectToJson.convert());
            }
    }
}
