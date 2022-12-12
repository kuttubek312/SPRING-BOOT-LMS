package com.peaksoft.springbootlms.dto.teacher_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherResponseView {

    List<TeacherResponse> responses;
}
