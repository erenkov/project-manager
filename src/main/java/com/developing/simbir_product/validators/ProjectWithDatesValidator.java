package com.developing.simbir_product.validators;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ProjectWithDatesValidator implements ConstraintValidator<ProjectWithDates, ProjectRequestDto> {

    @Override
    public void initialize(ProjectWithDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProjectRequestDto projectRequestDto, ConstraintValidatorContext context) {
        if (projectRequestDto.getStartDate() == null || projectRequestDto.getEstFinishDate() == null) {
            return true;
        }
        return projectRequestDto.getStartDate().isBefore(projectRequestDto.getEstFinishDate());
    }
}
