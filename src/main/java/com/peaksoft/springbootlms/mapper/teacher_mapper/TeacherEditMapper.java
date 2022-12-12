package com.peaksoft.springbootlms.mapper.teacher_mapper;

import com.peaksoft.springbootlms.dto.teacher_dto.TeacherRequest;
import com.peaksoft.springbootlms.model.Teacher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TeacherEditMapper {

    public Teacher create(TeacherRequest request) {
        if (request == null) {
            return null;
        }

        Teacher teacher = new Teacher();
        teacher.setLastName(request.getLastName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setCreated(LocalDate.now());
        teacher.setActive(true);
        return teacher;
    }

    public void update(Teacher teacher, TeacherRequest teacherRequest) {
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastName(teacherRequest.getLastName());
        teacher.setEmail(teacherRequest.getEmail());
    }
}
