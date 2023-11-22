package com.example.rest.rest.service.impl;

import com.example.rest.rest.aop.LoggableNews;
import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.News;
import com.example.rest.rest.repository.DatabaseNewsRepository;
import com.example.rest.rest.repository.NewsSpecification;
import com.example.rest.rest.service.NewsService;
import com.example.rest.rest.utils.BeanUtils;
import com.example.rest.rest.web.model.filter.NewsFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseNewsService implements NewsService {
    private final DatabaseNewsRepository newsRepository;

    @Override
    public List<News> filterBy(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public List<News> findAll(NewsFilter filter) {
        return newsRepository.findAll(PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Новость с ID: {0} не найдена", id)));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    @LoggableNews
    public News update(News news) {
        News existedNews = findById(news.getId());
        BeanUtils.copyNonNullProperties(news, existedNews);
        return news;
    }

    @Override
    @LoggableNews
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    @LoggableNews
    public void deleteByIdIn(List<Long> ids) {
        newsRepository.deleteAllById(ids);
    }
}
