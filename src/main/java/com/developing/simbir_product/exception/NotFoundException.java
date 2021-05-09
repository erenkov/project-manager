package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.RegistrationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(NotFoundException.class);
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
        logger.debug(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
