package com.example.rest.rest.validation;

import com.example.rest.rest.web.model.filter.UserFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class UserFilterValidValidator implements ConstraintValidator<UserFilterValid, UserFilter> {
    @Override
    public boolean isValid(UserFilter userFilter, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.anyNull(userFilter.getPageNumber(), userFilter.getPageSize())) {
            return false;
        }
        return true;
    }
}
