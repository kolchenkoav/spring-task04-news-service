package com.example.rest.rest.controller;

import com.example.rest.rest.mapper.UserMapper;
import com.example.rest.rest.model.User;
import com.example.rest.rest.service.UserService;
import com.example.rest.rest.web.model.filter.UserFilter;
import com.example.rest.rest.web.model.response.UserListResponse;
import com.example.rest.rest.web.model.response.UserResponse;
import com.example.rest.rest.web.model.upsert.UpsertUserRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API version")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(@Valid UserFilter filter) {
        return ResponseEntity.ok(
                mapper.userListToUserResponseList(service.findAll(filter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.userToResponse(service.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User newUser = service.save(mapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId,
                                               @RequestBody UpsertUserRequest request) {
        User updatedUser = service.update(mapper.requestToUser(userId, request));
        return ResponseEntity.ok(mapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
