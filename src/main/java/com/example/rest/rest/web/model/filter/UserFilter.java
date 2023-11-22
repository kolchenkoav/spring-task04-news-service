package com.example.rest.rest.web.model.filter;

import com.example.rest.rest.validation.UserFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserFilterValid
public class UserFilter {
    private Integer pageSize;
    private Integer pageNumber;
}
