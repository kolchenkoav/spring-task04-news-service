package com.example.rest.rest.mapper;

import com.example.rest.rest.model.Comment;
import com.example.rest.rest.model.News;

import com.example.rest.rest.service.CommentService;
import com.example.rest.rest.service.GroupService;
import com.example.rest.rest.service.UserService;
import com.example.rest.rest.web.model.response.NewsListResponse;
import com.example.rest.rest.web.model.response.NewsResponse;
import com.example.rest.rest.web.model.response.NewsResponseCountComments;
import com.example.rest.rest.web.model.upsert.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class NewsMapperDelegate implements NewsMapper {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setMessage(request.getMessage());
        news.setGroup(groupService.findById(request.getGroupId()));
        news.setUser(userService.findById(request.getUserId()));
        return news;
    }

    @Override
    public News requestToNews(Long id, UpsertNewsRequest request) {
        News order = requestToNews(request);
        order.setId(id);
        return order;
    }

    //==============================================================
    //  Формирует такой JSON для ответа клиенту
    //{
    //    "id": 1,
    //    "message": "Xxxxxxxxxxxxxxxx",
    //    "groupId": 2,
    //    "userId": 1,
    //    "comments": [
    //        {
    //            "id": 5,
    //            "comment": "Yyyyyyyyyy"
    //        }
    //    ]
    //}
    //==============================================================
    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());                                           //  ID
        newsResponse.setMessage(news.getMessage());                                 //  Новость
        newsResponse.setGroupId(news.getGroup().getId());                           //  ID категории новости
        newsResponse.setUserId(news.getUser().getId());                             //  ID пользователя
        //  Получаем список комментариев по ID новости
        List<Comment> commentList = commentService.findByNewsId(news.getId());
        newsResponse.setComments(commentMapper.commentListToResponse(commentList)); //  и записываем в новость
        return newsResponse;
    }

    @Override
    public NewsListResponse newsListToNewsResponseList(List<News> newsList) {
        return NewsMapper.super.newsListToNewsResponseList(newsList);
    }

    //============================
    //{
    //    "id": 4,
    //    "message": "Zzzzzzz",
    //    "groupId": 5,
    //    "userId": 1,
    //    "countComments": 4
    //}
    //============================
    @Override
    public NewsResponseCountComments newsToResponseCountComments(News news) {
        NewsResponseCountComments newsResponseCountComments = new NewsResponseCountComments();
        newsResponseCountComments.setId(news.getId());                          //  ID
        newsResponseCountComments.setMessage(news.getMessage());                //  Новость
        newsResponseCountComments.setGroupId(news.getGroup().getId());          //  ID категории новости
        newsResponseCountComments.setUserId(news.getUser().getId());            //  ID пользователя
        List<Comment> commentList = commentService.findByNewsId(news.getId());  //  Получаем список комментариев
        newsResponseCountComments.setCountComments(commentList.size());         //  Записываем количество
        return newsResponseCountComments;
    }
}
