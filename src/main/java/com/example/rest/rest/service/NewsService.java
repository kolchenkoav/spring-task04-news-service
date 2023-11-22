package com.example.rest.rest.service;

import com.example.rest.rest.model.News;
import com.example.rest.rest.web.model.filter.NewsFilter;

import java.util.List;

public interface NewsService {
    List<News> findAll(NewsFilter filter);
    News findById(Long id);
    News save(News news);
    News update(News news);
    void deleteById(Long id);
    void deleteByIdIn(List<Long> ids);

    List<News> filterBy(NewsFilter filter);
}
