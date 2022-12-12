package com.peaksoft.springbootlms.dto.student_dto;

import com.peaksoft.springbootlms.model.enam.StudyFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {

    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    private StudyFormat studyFormat;

    @CreatedDate
    private LocalDate created;

    private boolean idActive;
}
