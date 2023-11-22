package com.example.rest.rest.service;
import com.example.rest.rest.model.User;
import com.example.rest.rest.web.model.filter.UserFilter;

import java.util.List;

public interface UserService {
    List<User> findAll(UserFilter filter);
    User findById(Long id);
    User save(User user);
    User update(User user);
    void deleteById(Long id);
}
