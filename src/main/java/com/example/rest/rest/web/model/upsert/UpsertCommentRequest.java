package com.example.rest.rest.web.model.upsert;

import lombok.Data;

@Data
public class UpsertCommentRequest {
    private Long newsId;
    private String comment;
    private Long userId;
}
