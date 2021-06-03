package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;


public class ProjectAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(ProjectAlreadyExistException.class);
    private final MessageSource messageSource;
    private final ProjectRequestDto project;

    public ProjectAlreadyExistException(String message, ProjectRequestDto project, MessageSource messageSource) {
        super(message);
        this.project = project;
        this.messageSource = messageSource;
        logger.info(messageSource.getMessage(message, new String[]{project.getName()}, Locale.getDefault()));
    }

    public ProjectRequestDto getProject() {
        return project;
    }

    @Override
    public String getLocalizedMessage() {
        return messageSource.getMessage(super.getMessage(), new String[]{project.getName()},
                LocaleContextHolder.getLocale());
    }
}
