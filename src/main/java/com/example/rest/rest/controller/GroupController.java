package com.example.rest.rest.controller;

import com.example.rest.rest.mapper.GroupMapper;
import com.example.rest.rest.model.Group;
import com.example.rest.rest.service.GroupService;
import com.example.rest.rest.web.model.filter.GroupFilter;
import com.example.rest.rest.web.model.response.GroupListResponse;
import com.example.rest.rest.web.model.response.GroupResponse;
import com.example.rest.rest.web.model.upsert.UpsertGroupRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group")
@RequiredArgsConstructor
@Tag(name = "Group", description = "Group API version")
public class GroupController {
    private final GroupService service;
    private final GroupMapper mapper;

    @GetMapping("/filter")
    public ResponseEntity<GroupListResponse> filterBy(@Valid GroupFilter filter) {
        return ResponseEntity.ok(
                mapper.GroupToGroupResponseList(service.filterBy(filter))
        );
    }

    @GetMapping
    public ResponseEntity<GroupListResponse> findAll(@Valid GroupFilter filter) {
        return ResponseEntity.ok(
                mapper.GroupToGroupResponseList(service.findAll(filter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.GroupToResponse(service.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<GroupResponse> create(@RequestBody @Valid UpsertGroupRequest request) {
        Group newGroup = service.save(mapper.requestToGroup(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.GroupToResponse(newGroup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponse> update(@PathVariable("id") Long newsCategoryId,
                                                @RequestBody @Valid UpsertGroupRequest request) {
        Group updatedGroup = service.update(mapper.requestToGroup(newsCategoryId, request));
        return ResponseEntity.ok(mapper.GroupToResponse(updatedGroup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
