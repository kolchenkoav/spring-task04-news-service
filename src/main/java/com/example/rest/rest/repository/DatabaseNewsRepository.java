package com.example.rest.rest.repository;

import com.example.rest.rest.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseNewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {
}
