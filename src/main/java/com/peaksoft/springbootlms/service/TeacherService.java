package com.peaksoft.springbootlms.service;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherRequest;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherResponse;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherResponseView;
import com.peaksoft.springbootlms.exception.not_found.CompanyNotFoundException;
import com.peaksoft.springbootlms.exception.not_found.TeacherNotFoundException;
import com.peaksoft.springbootlms.mapper.teacher_mapper.TeacherEditMapper;
import com.peaksoft.springbootlms.mapper.teacher_mapper.TeacherViewMapper;
import com.peaksoft.springbootlms.model.Course;
import com.peaksoft.springbootlms.model.Teacher;
import com.peaksoft.springbootlms.repository.CourseRepository;
import com.peaksoft.springbootlms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherEditMapper editMapper;
    private final TeacherViewMapper viewMapper;
    private final CourseRepository courseRepository;

    public TeacherResponse create(TeacherRequest teacherRequest) {
        Teacher teacher = editMapper.create(teacherRequest);
        teacher.setCourse(setTeacherForCourse(teacherRequest.getCourseId()));
        repository.save(teacher);
        return viewMapper.viewTeacher(teacher);
    }

    private Course setTeacherForCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new TeacherNotFoundException(courseId));
    }

    public TeacherResponse update(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = getTeacherById(id);
        editMapper.update(teacher, teacherRequest);
        return viewMapper.viewTeacher(repository.save(teacher));
    }

    private Teacher getTeacherById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    public TeacherResponse findById(Long id) {
        Teacher teacher = getTeacherById(id);
        return viewMapper.viewTeacher(teacher);
    }

    public ResponseDelete deleteById(Long id) {
        boolean exist = repository.existsById(id);
        if (!exist) {
            throw new CompanyNotFoundException(id);
        }
        repository.deleteById(id);
        return new ResponseDelete(
                "DELETED",
                "Successfully deleted teacher");
    }

    public List<TeacherResponse> getAllTeachers() {
        return viewMapper.view(repository.findAll());
    }

    public TeacherResponseView getAllTeachersPagination(String text, int page, int size) {
        TeacherResponseView responseView = new TeacherResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers) {
            responses.add(viewMapper.viewTeacher(teacher));
        }
        return responses;
    }

    public List<Teacher> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}