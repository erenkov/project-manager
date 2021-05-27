package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(UserAlreadyExistException.class);
    private final UserRequestDto user;

    public UserAlreadyExistException(String message, UserRequestDto user) {
        super(message);
        this.user = user;
        logger.info(message);
    }

    public UserRequestDto getUser() {
        return user;
    }
}
