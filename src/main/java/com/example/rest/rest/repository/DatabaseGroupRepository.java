package com.example.rest.rest.repository;

import com.example.rest.rest.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseGroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {
}
