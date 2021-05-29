package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskDatesException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(TaskDatesException.class);
    private final TaskRequestDto task;

    public TaskDatesException(String message, TaskRequestDto task) {
        super(message);
        this.task = task;
        logger.info(message);
    }

    public TaskRequestDto getTask() {
        return task;
    }
}
