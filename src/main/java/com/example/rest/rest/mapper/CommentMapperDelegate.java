package com.example.rest.rest.mapper;

import com.example.rest.rest.model.Comment;
import com.example.rest.rest.service.NewsService;
import com.example.rest.rest.service.UserService;
import com.example.rest.rest.web.model.response.CommentResponse;
import com.example.rest.rest.web.model.upsert.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class CommentMapperDelegate implements CommentMapper {
    @Autowired
    private NewsService databaseNewsService;
    @Autowired
    private UserService databaseUserService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setNews(databaseNewsService.findById(request.getNewsId()));
        comment.setUser(databaseUserService.findById(request.getUserId()));
        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);
        return comment;
    }

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setComment(comment.getComment());
        commentResponse.setNewsId(comment.getNews().getId());
        commentResponse.setUserId(comment.getUser().getId());
        return commentResponse;
    }
}
