package com.peaksoft.springbootlms.service;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.group_dto.GroupRequest;
import com.peaksoft.springbootlms.dto.group_dto.GroupResponse;
import com.peaksoft.springbootlms.dto.group_dto.GroupResponseView;
import com.peaksoft.springbootlms.exception.not_found.CompanyNotFoundException;
import com.peaksoft.springbootlms.exception.not_found.GroupNotFoundException;
import com.peaksoft.springbootlms.mapper.group_mapper.GroupEditMapper;
import com.peaksoft.springbootlms.mapper.group_mapper.GroupViewMapper;
import com.peaksoft.springbootlms.model.Course;
import com.peaksoft.springbootlms.model.Group;
import com.peaksoft.springbootlms.repository.CourseRepository;
import com.peaksoft.springbootlms.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;
    private final CourseRepository courseRepository;

    public GroupResponse create(GroupRequest groupRequest) {
        Group group = editMapper.create(groupRequest);
        group.setCourses(setGroupForCourse(groupRequest.getCourseId()));
        repository.save(group);
        return viewMapper.viewGroup(group);
    }

    private List<Course> setGroupForCourse(List<Long> courseId) {
        List<Course> courses = new ArrayList<>();
        for (Long course : courseId) {
            courses.add(courseRepository.findById(course)
                    .orElseThrow(() -> new GroupNotFoundException(courseId)));

        }
        return courses;
    }

    public GroupResponse update(Long id, GroupRequest groupRequest) {
        Group group = getGroupById(id);
        editMapper.update(group, groupRequest);
        return viewMapper.viewGroup(repository.save(group));
    }

    private Group getGroupById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }

    public GroupResponse findById(Long id) {
        Group group = getGroupById(id);
        return viewMapper.viewGroup(group);
    }

    public ResponseDelete deleteById(Long id) {
        boolean exist = repository.existsById(id);
        if (!exist) {
            throw new CompanyNotFoundException(id);
        }
        repository.deleteById(id);
        return new ResponseDelete(
                "DELETED", "Successfully deleted group"
        );
    }

    public List<GroupResponse> getAllCourse() {
        return viewMapper.view(repository.findAll());
    }

    public GroupResponseView getAllGroupPagination(String text, int page, int size) {
        GroupResponseView responseView = new GroupResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<GroupResponse> view(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(viewMapper.viewGroup(group));
        }
        return responses;
    }

    public List<Group> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
