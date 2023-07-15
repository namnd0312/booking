package com.namnd.bookingbe.utils;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@NotNull
@NotEmpty
public @interface CustomValidation {

    String message() default "Custom validation error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
