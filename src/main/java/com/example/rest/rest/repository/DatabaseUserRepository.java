package com.example.rest.rest.repository;

import com.example.rest.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
