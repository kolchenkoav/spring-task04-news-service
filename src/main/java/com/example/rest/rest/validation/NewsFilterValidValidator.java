package com.example.rest.rest.validation;

import com.example.rest.rest.web.model.filter.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class NewsFilterValidValidator implements ConstraintValidator<NewsFilterValid, NewsFilter> {
    @Override
    public boolean isValid(NewsFilter newsFilter, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.anyNull(newsFilter.getPageNumber(), newsFilter.getPageSize())) {
            return false;
        }

        return true;
    }
}
