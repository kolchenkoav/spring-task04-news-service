package com.example.rest.rest.web.model.filter;

import com.example.rest.rest.validation.GroupFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@GroupFilterValid
public class GroupFilter {
    private Integer pageSize;
    private Integer pageNumber;
    private String name;
}
