package com.example.rest.rest.web.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentListResponse {
    private List<CommentResponse> comments = new ArrayList<>();
}
