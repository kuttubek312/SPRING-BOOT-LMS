package com.peaksoft.springbootlms.mapper.student_mapper;

import com.peaksoft.springbootlms.dto.student_dto.StudentRequest;
import com.peaksoft.springbootlms.model.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StudentEditMapper {

    public Student create(StudentRequest request) {
        if (request == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
        student.setCreated(LocalDate.now());
        student.setIdActive(true);
        return student;
    }

    public void update(Student student, StudentRequest studentRequest) {
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
    }
}
