package com.example.rest.rest.service.impl;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.User;
import com.example.rest.rest.repository.DatabaseUserRepository;
import com.example.rest.rest.service.UserService;
import com.example.rest.rest.utils.BeanUtils;
import com.example.rest.rest.web.model.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseUserService implements UserService {
    private final DatabaseUserRepository userRepository;

    @Override
    public List<User> findAll(UserFilter filter) {
        return userRepository.findAll(PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Пользователь с ID: {0} не найден", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
