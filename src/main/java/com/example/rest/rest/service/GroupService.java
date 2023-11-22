package com.example.rest.rest.service;

import com.example.rest.rest.model.Group;
import com.example.rest.rest.web.model.filter.GroupFilter;;

import java.util.List;

public interface GroupService {
    List<Group> filterBy(GroupFilter filter);

    List<Group> findAll(GroupFilter filter);
    Group findById(Long id);
    Group save(Group group);
    Group update(Group group);
    void deleteById(Long id);
}
