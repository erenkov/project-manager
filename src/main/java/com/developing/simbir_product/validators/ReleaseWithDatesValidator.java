package com.developing.simbir_product.validators;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ReleaseWithDatesValidator implements ConstraintValidator<ReleaseWithDates, ReleaseRequestDto> {

    @Override
    public void initialize(ReleaseWithDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(ReleaseRequestDto releaseRequestDto, ConstraintValidatorContext context) {
        if (releaseRequestDto.getStartDate() == null || releaseRequestDto.getFinishDate() == null) {
            return false;
        }
        return releaseRequestDto.getStartDate().isBefore(releaseRequestDto.getFinishDate());
    }
}
