package com.peaksoft.springbootlms.dto.course_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseView {

    List<CourseResponse> responses;
}
