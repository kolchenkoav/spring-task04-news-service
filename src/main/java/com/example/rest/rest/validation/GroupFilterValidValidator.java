package com.example.rest.rest.validation;

import com.example.rest.rest.web.model.filter.GroupFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class GroupFilterValidValidator implements ConstraintValidator<GroupFilterValid, GroupFilter> {
    @Override
    public boolean isValid(GroupFilter groupFilter, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.anyNull(groupFilter.getPageNumber(), groupFilter.getPageSize())) {
            return false;
        }
        return true;
    }
}
