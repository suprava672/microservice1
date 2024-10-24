package com.Blog.User.exception;

import com.Blog.User.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> globalExceptionHandler(ResourceNotFoundException rx, WebRequest wx){
//        String message = rx.getMessage();
//
//        ApiResponse apiResponse = new ApiResponse(message,true);
//        return new  ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        Map<String, Object> body = new HashMap<>();
        body.put("message",rx.getMessage());
        return  new ResponseEntity<>(body,HttpStatus.NOT_FOUND);


    }

}
