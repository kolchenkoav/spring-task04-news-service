package com.example.rest.rest.mapper;

import com.example.rest.rest.model.News;
import com.example.rest.rest.web.model.response.*;
import com.example.rest.rest.web.model.upsert.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapper.class, UserMapper.class, GroupMapper.class})
public interface NewsMapper {
    News requestToNews(UpsertNewsRequest request);

    News requestToNews(Long id, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    NewsResponseCountComments newsToResponseCountComments(News news);

    default NewsListResponse newsListToNewsResponseList(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();

        response.setNewsList(newsList.stream()
                .map(this::newsToResponseCountComments).toList());

        return response;
    }
}
