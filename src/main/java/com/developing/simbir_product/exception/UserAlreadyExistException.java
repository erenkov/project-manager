package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;


public class UserAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(UserAlreadyExistException.class);
    private final MessageSource messageSource;
    private final UserRequestDto user;

    public UserAlreadyExistException(String message, UserRequestDto user, MessageSource messageSource) {
        super(message);
        this.user = user;
        this.messageSource = messageSource;
        logger.info(messageSource.getMessage(message, new String[]{user.getEmail()}, Locale.getDefault()));
    }

    public UserRequestDto getUser() {
        return user;
    }

    @Override
    public String getLocalizedMessage() {
        return messageSource.getMessage(super.getMessage(), new String[]{user.getEmail()},
                LocaleContextHolder.getLocale());
    }
}
