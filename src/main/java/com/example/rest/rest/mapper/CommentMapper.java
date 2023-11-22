package com.example.rest.rest.mapper;

import com.example.rest.rest.web.model.response.CommentListResponse;
import com.example.rest.rest.web.model.response.CommentResponse;
import com.example.rest.rest.web.model.upsert.UpsertCommentRequest;
import com.example.rest.rest.model.Comment;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    default List<CommentResponse> commentListToResponse(List<Comment> comments) {
        List<CommentResponse> list = new ArrayList<>();
        for (Comment c: comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(c.getId());
            commentResponse.setComment(c.getComment());
            commentResponse.setNewsId(c.getNews().getId());
            commentResponse.setUserId(c.getUser().getId());
            list.add(commentResponse);
        }
        return list;
    }

    //========================================================
    //{
    //    "comments": [
    //        {
    //            "id": 7,
    //            "comment": "Я и не сомневался",
    //            "userId": 3,
    //            "newsId": 6
    //        },
    //        {
    //            "id": 6,
    //            "comment": "Молодец. Так держать",
    //            "userId": 4,
    //            "newsId": 6
    //        }
    //    ]
    //}
    //========================================================
    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();
        response.setComments(comments.stream().
                map(this::commentToResponse).collect(Collectors.toList()));
        return response;
    }
}
