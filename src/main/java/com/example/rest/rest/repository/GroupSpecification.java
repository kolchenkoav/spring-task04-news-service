package com.example.rest.rest.repository;

import com.example.rest.rest.model.Group;
import com.example.rest.rest.web.model.filter.GroupFilter;
import org.springframework.data.jpa.domain.Specification;

public interface GroupSpecification {
    static Specification<Group> withFilter(GroupFilter groupFilter) {
        return Specification.where(ByGroupName(groupFilter.getName()));
    }

    static Specification<Group> ByGroupName(String groupName) {
        return (root, query, cb) -> {
            if (groupName == null) {
                return null;
            }
            return cb.equal(root.get("name"), groupName);
        };
    }
}
