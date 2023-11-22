package com.example.rest.rest.web.model.filter;

import com.example.rest.rest.validation.NewsFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@NewsFilterValid
public class NewsFilter {
    private Integer pageSize;
    private Integer pageNumber;
    private Long groupId;
    private Long userId;
    private Instant createAtBefore;
    private Instant updateAtBefore;
}
