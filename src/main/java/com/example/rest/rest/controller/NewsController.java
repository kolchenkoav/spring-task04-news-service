package com.example.rest.rest.controller;

import com.example.rest.rest.mapper.NewsMapper;
import com.example.rest.rest.model.News;
import com.example.rest.rest.service.NewsService;
import com.example.rest.rest.web.model.filter.NewsFilter;
import com.example.rest.rest.web.model.response.NewsListResponse;
import com.example.rest.rest.web.model.response.NewsResponse;
import com.example.rest.rest.web.model.upsert.UpsertNewsRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "News API version")
public class NewsController {
    private final NewsService service;
    private final NewsMapper mapper;

    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(@Valid NewsFilter filter) {
        return ResponseEntity.ok(
                mapper.newsListToNewsResponseList(service.filterBy(
                        filter
                ))
        );
    }

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter) {
        return ResponseEntity.ok(
                mapper.newsListToNewsResponseList(service.findAll(filter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable("id") Long newsId) {
        return ResponseEntity.ok(
                mapper.newsToResponse(service.findById(newsId))
        );
    }

    @PostMapping
    public ResponseEntity<NewsResponse> save(@RequestBody UpsertNewsRequest request) {
        News newNews = service.save(mapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.newsToResponse(newNews));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<NewsResponse> update(@PathVariable("id") Long newsId,
                                               @RequestBody UpsertNewsRequest request) {
        News updatedNews = service.update(mapper.requestToNews(newsId, request));
        return ResponseEntity.ok(mapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long newsId) {
        service.deleteById(newsId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
