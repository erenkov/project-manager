package com.developing.simbir_product.validators;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class TaskWithDatesValidator implements ConstraintValidator<TaskWithDates, TaskRequestDto> {

    @Override
    public void initialize(TaskWithDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(TaskRequestDto taskRequestDto, ConstraintValidatorContext context) {
        if (taskRequestDto.getDueDate() == null || taskRequestDto.getCreateDate() == null) {
            return false;
        }
        return taskRequestDto.getCreateDate().isBefore(taskRequestDto.getDueDate());
    }
}
