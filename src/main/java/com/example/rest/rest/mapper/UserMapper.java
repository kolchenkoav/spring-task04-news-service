package com.example.rest.rest.mapper;

import com.example.rest.rest.model.User;
import com.example.rest.rest.web.model.response.UserListResponse;
import com.example.rest.rest.web.model.response.UserResponse;
import com.example.rest.rest.web.model.upsert.UpsertUserRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(UserMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapper.class, NewsMapper.class})
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);

    User requestToUser(Long id, UpsertUserRequest request);

    UserResponse userToResponse(User user);

    List<UserResponse> userListToResponse(List<User> users);

    default UserListResponse userListToUserResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(userListToResponse(users));
        return response;
    }
}
