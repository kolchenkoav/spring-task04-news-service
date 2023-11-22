package com.example.rest.rest.mapper;

import com.example.rest.rest.model.Group;
import com.example.rest.rest.web.model.upsert.UpsertGroupRequest;

public abstract class GroupMapperDelegate implements GroupMapper {
    @Override
    public Group requestToGroup(UpsertGroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        return group;
    }

    @Override
    public Group requestToGroup(Long newsCategoryId, UpsertGroupRequest request) {
        Group group = requestToGroup(request);
        group.setId(newsCategoryId);
        return group;
    }
}
