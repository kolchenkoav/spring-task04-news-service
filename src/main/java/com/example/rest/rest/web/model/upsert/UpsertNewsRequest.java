package com.example.rest.rest.web.model.upsert;

import lombok.Data;

@Data
public class UpsertNewsRequest {
    private String message;
    private Long groupId;
    private Long userId;
}
