package com.example.rest.rest.service;
import com.example.rest.rest.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment save(Comment comment);
    Comment update(Comment comment);
    List<Comment> findByNewsId(Long newsId);
    void deleteById(Long id);
    void deleteByIdIn(List<Long> ids);
}
