package com.example.rest.rest.mapper;

import com.example.rest.rest.model.User;
import com.example.rest.rest.web.model.upsert.UpsertUserRequest;

public abstract class UserMapperDelegate implements UserMapper {
    @Override
    public User requestToUser(UpsertUserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        return user;
    }

    @Override
    public User requestToUser(Long userId, UpsertUserRequest request) {
        User user = requestToUser(request);
        user.setId(userId);
        return user;
    }
}
