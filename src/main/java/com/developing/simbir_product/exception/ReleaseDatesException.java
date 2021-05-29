package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReleaseDatesException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(ReleaseDatesException.class);
    private final ReleaseRequestDto release;

    public ReleaseDatesException(String message, ReleaseRequestDto release) {
        super(message);
        this.release = release;
        logger.info(message);
    }

    public ReleaseRequestDto getRelease() {
        return release;
    }
}
