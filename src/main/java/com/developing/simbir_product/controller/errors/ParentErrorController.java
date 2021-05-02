package com.developing.simbir_product.controller.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParentErrorController {
    @ExceptionHandler(Exception.class) // TODO: use custom exceptions
    public ResponseEntity handleException(Exception e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
