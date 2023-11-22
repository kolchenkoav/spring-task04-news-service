package com.example.rest.rest.repository;

import com.example.rest.rest.model.News;
import com.example.rest.rest.web.model.filter.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface NewsSpecification {
    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(ByGroupId(newsFilter.getGroupId()))
                .and(byUserId(newsFilter.getUserId()))
                .and(byCreateAtBefore(newsFilter.getCreateAtBefore()))
                .and(byUpdateAtBefore(newsFilter.getUpdateAtBefore()));
    }

    static Specification<News> ByGroupId(Long groupId) {
        return (root, query, cb) -> {
            if (groupId == null) {
                return null;
            }
            return cb.equal(root.get("group").get("id"), groupId);
        };
    }

    static Specification<News> byUserId(Long userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return null;
            }
            return cb.equal(root.get("user").get("id"), userId);
        };
    }

    static Specification<News> byCreateAtBefore(Instant createdBefore) {
        return (root, query, cb) -> {
            if (createdBefore == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get("createdAt"), createdBefore);
        };
    }
    static Specification<News> byUpdateAtBefore(Instant updatedBefore) {
        return (root, query, cb) -> {
            if (updatedBefore == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get("updatedAt"), updatedBefore);
        };
    }
}
