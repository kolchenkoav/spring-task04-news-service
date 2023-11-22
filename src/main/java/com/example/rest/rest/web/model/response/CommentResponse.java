package com.example.rest.rest.web.model.response;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String comment;

    private Long userId;

    private Long newsId;
}
