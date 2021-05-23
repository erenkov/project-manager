package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProjectAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(NotFoundException.class);
    private final ProjectRequestDto project;

    public ProjectAlreadyExistException(String message, ProjectRequestDto project) {
        super(message);
        this.project = project;
        logger.error(message);
    }

    public ProjectRequestDto getProject() {
        return project;
    }
}
