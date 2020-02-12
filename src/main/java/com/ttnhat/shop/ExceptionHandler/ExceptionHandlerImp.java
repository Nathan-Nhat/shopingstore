package com.ttnhat.shop.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.tomcat.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerImp extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ExceptionMessageObject> handleUserNotfoundException(Exception ex, HttpServletRequest request){
        String path = request.getServletPath();
        Date date = new Date();
        ExceptionMessageObject exceptionObject = new ExceptionMessageObject(
                date, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                ErrorType.USER_NOTFOUND.toString(), ex.getMessage(), path, new DebugLog(ex).getLog()
        );

        return new ResponseEntity<ExceptionMessageObject>(
                exceptionObject,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler({ExpiredJwtException.class, SignatureException.class})
    public ResponseEntity<ExceptionMessageObject> handleInvalidToken(Exception ex, HttpServletRequest request){
        String path = request.getServletPath();
        Date date = new Date();
        ExceptionMessageObject exceptionObject = new ExceptionMessageObject(
                date, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                ErrorType.INVALID_JWT_TOKEN.toString(), ex.getMessage(), path, new DebugLog(ex).getLog()
        );

        return new ResponseEntity<ExceptionMessageObject>(
                exceptionObject,
                new HttpHeaders(),
                HttpStatus.FORBIDDEN
        );
    }
}
