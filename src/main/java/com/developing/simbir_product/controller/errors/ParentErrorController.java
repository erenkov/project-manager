package com.developing.simbir_product.controller.errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ParentErrorController {

    // Данный метод на стадии разработки! Данил в курсе
    @ExceptionHandler(Exception.class) // TODO: use custom exceptions
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleException(Exception e) {
        return "error-404";
    }


}
