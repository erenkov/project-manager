package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;


public class TaskDatesException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(TaskDatesException.class);
    private final MessageSource messageSource;
    private final TaskRequestDto task;

    public TaskDatesException(String message, TaskRequestDto task, MessageSource messageSource) {
        super(message);
        this.task = task;
        this.messageSource = messageSource;
        logger.info(messageSource.getMessage(message, new String[]{task.getName()}, Locale.getDefault()));
    }

    public TaskRequestDto getTask() {
        return task;
    }

    @Override
    public String getLocalizedMessage() {
        return messageSource.getMessage(super.getMessage(), new String[]{task.getName()},
                LocaleContextHolder.getLocale());
    }
}
