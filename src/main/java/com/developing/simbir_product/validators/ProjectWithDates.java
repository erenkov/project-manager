package com.developing.simbir_product.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProjectWithDatesValidator.class})
public @interface ProjectWithDates {

    String message() default "{projectWithDates.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
