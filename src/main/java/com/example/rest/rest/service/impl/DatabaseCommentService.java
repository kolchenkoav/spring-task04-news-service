package com.example.rest.rest.service.impl;

import com.example.rest.rest.aop.LoggableComments;
import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Comment;
import com.example.rest.rest.repository.DatabaseCommentRepository;
import com.example.rest.rest.service.CommentService;
import com.example.rest.rest.utils.BeanUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseCommentService implements CommentService {
    private final DatabaseCommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Комментарий с ID: {0} не найден", id)));
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @LoggableComments
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
        BeanUtils.copyNonNullProperties(comment, existedComment);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByNewsId(Long newsId) {
        return commentRepository.findByNewsId(newsId);
    }

    @Override
    @LoggableComments
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        //commentRepository.deleteByIdIn(ids); //TODO deleteByIdIn
    }
}
