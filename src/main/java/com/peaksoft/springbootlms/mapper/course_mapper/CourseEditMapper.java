package com.peaksoft.springbootlms.mapper.course_mapper;

import com.peaksoft.springbootlms.dto.course_dto.CourseRequest;
import com.peaksoft.springbootlms.model.Course;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CourseEditMapper {

    public Course create(CourseRequest request) {

        if (request == null) {
            return null;
        }

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        course.setCreated(LocalDate.now());
        course.setActive(true);
        return course;
    }

    public void update(Course course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());

    }
}
