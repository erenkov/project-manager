package com.developing.simbir_product.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ReleaseWithDatesValidator.class})
public @interface ReleaseWithDates {

    String message() default "Start date is after finish date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
