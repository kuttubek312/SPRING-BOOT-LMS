package com.peaksoft.springbootlms.dto.student_dto;

import com.peaksoft.springbootlms.model.enam.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    private String firstName;

    private String email;

    private String lastName;

    private StudyFormat studyFormat;

    private Long groupId;
}
