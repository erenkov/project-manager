package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;


public class ReleaseDatesException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(ReleaseDatesException.class);
    private final MessageSource messageSource;
    private final ReleaseRequestDto release;

    public ReleaseDatesException(String message, ReleaseRequestDto release, MessageSource messageSource) {
        super(message);
        this.release = release;
        this.messageSource = messageSource;
        logger.info(messageSource.getMessage(message, new String[]{release.getName()}, Locale.getDefault()));
    }

    public ReleaseRequestDto getRelease() {
        return release;
    }

    @Override
    public String getLocalizedMessage() {
        return messageSource.getMessage(super.getMessage(), new String[]{release.getName()},
                LocaleContextHolder.getLocale());
    }
}
