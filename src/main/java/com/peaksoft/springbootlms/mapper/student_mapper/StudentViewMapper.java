package com.peaksoft.springbootlms.mapper.student_mapper;

import com.peaksoft.springbootlms.dto.student_dto.StudentResponse;
import com.peaksoft.springbootlms.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentViewMapper {

    public StudentResponse viewStudent(Student student) {
        if (student == null) {
            return null;
        }

        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setLastName(student.getLastName());
        response.setFirstName(student.getFirstName());
        response.setEmail(student.getEmail());
        response.setStudyFormat(student.getStudyFormat());
        response.setIdActive(student.isIdActive());
        response.setCreated(student.getCreated());
        return response;
    }

    public List<StudentResponse> view(List<Student> students) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student : students) {
            responses.add(viewStudent(student));
        }
        return responses;
    }
}
