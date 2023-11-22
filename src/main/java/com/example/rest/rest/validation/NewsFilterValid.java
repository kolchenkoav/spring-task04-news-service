package com.example.rest.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NewsFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewsFilterValid {
    String message() default "Поля пагинации должны быть указаны.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
