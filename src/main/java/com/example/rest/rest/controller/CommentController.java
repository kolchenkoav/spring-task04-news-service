package com.example.rest.rest.controller;

import com.example.rest.rest.mapper.CommentMapper;
import com.example.rest.rest.model.Comment;
import com.example.rest.rest.service.CommentService;
import com.example.rest.rest.web.model.response.CommentListResponse;
import com.example.rest.rest.web.model.response.CommentResponse;
import com.example.rest.rest.web.model.upsert.UpsertCommentRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    final private CommentService commentService;
    final private CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<CommentListResponse> findByNewsId(@RequestParam Long newsId) {
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(commentService.findByNewsId(newsId))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody UpsertCommentRequest request) {
        Comment newNews = commentService.save(commentMapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(newNews));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CommentResponse> update(@PathVariable("id") Long commentId,
                                               @RequestBody UpsertCommentRequest request) {
        Comment updatedNes = commentService.update(commentMapper.requestToComment(commentId, request));
        return ResponseEntity.ok(commentMapper.commentToResponse(updatedNes));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
