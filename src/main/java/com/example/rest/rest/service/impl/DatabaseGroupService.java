package com.example.rest.rest.service.impl;

import com.example.rest.rest.exception.EntityNotFoundException;
import com.example.rest.rest.model.Group;
import com.example.rest.rest.repository.DatabaseGroupRepository;
import com.example.rest.rest.repository.GroupSpecification;
import com.example.rest.rest.service.GroupService;
import com.example.rest.rest.utils.BeanUtils;
import com.example.rest.rest.web.model.filter.GroupFilter;
import com.example.rest.rest.web.model.filter.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DatabaseGroupService implements GroupService {
    private final DatabaseGroupRepository groupRepository;


    @Override
    public List<Group> filterBy(GroupFilter filter) {
        return groupRepository.findAll(GroupSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public List<Group> findAll(GroupFilter filter) {
        return groupRepository.findAll(PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat
                        .format("Категория с ID: {0} не найдена", id)));
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) {
        Group existedGroup = findById(group.getId());
        BeanUtils.copyNonNullProperties(group, existedGroup);
        return groupRepository.save(group);
    }

    @Override
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }
}
