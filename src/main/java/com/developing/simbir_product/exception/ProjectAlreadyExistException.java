package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProjectAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(ProjectAlreadyExistException.class);
    private final ProjectRequestDto project;

    public ProjectAlreadyExistException(String message, ProjectRequestDto project) {
        super(message);
        this.project = project;
        logger.info(message);
    }

    public ProjectRequestDto getProject() {
        return project;
    }
}
