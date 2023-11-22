package com.example.rest.rest.web.model.response;

import lombok.Data;

@Data
public class NewsResponseCountComments {
    private Long id;
    private String message;
    private Long groupId;
    private Long userId;
    private int countComments;
}
