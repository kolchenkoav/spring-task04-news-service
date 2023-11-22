package com.example.rest.rest.mapper;

import com.example.rest.rest.model.Group;
import com.example.rest.rest.web.model.response.GroupListResponse;
import com.example.rest.rest.web.model.response.GroupResponse;
import com.example.rest.rest.web.model.upsert.UpsertGroupRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(GroupMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {
    Group requestToGroup(UpsertGroupRequest request);

    Group requestToGroup(Long id, UpsertGroupRequest request);

    GroupResponse GroupToResponse(Group group);

    List<GroupResponse> GroupListToResponse(List<Group> newsCategories);

    default GroupListResponse GroupToGroupResponseList(List<Group> newsCategories) {
        GroupListResponse response = new GroupListResponse();
        response.setGroupResponses(GroupListToResponse(newsCategories));
        return response;
    }
}
