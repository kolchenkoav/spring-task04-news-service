package com.example.rest.rest.web.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class NewsResponse {
    private Long id;
    private String message;
    private Long groupId;
    private Long userId;
    private List<CommentResponse> comments = new ArrayList<>();
}
