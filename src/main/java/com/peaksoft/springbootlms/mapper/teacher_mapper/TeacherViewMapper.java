package com.peaksoft.springbootlms.mapper.teacher_mapper;

import com.peaksoft.springbootlms.dto.teacher_dto.TeacherRequest;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherResponse;
import com.peaksoft.springbootlms.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherViewMapper {

    public TeacherResponse viewTeacher(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setFirstName(teacher.getFirstName());
        response.setLastName(teacher.getLastName());
        response.setEmail(teacher.getEmail());
        response.setActive(teacher.isActive());
        response.setCreated(teacher.getCreated());
        return response;
    }

    public List<TeacherResponse> view(List<Teacher> teachers) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher : teachers) {
            responses.add(viewTeacher(teacher));
        }
        return responses;
    }
}
