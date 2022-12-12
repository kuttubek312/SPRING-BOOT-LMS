package com.peaksoft.springbootlms.controller;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.course_dto.CourseRequest;
import com.peaksoft.springbootlms.dto.course_dto.CourseResponse;
import com.peaksoft.springbootlms.dto.course_dto.CourseResponseView;
import com.peaksoft.springbootlms.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Course API",
        description = "User with role admin,editor can create, update, delete or get all course ")
public class CourseController {

    private final CourseService service;

    @PostMapping("/save")
    @Operation(summary = "create course", description = "we can create course")
    public CourseResponse create(@RequestBody CourseRequest request) {
        return service.create(request.getCompanyId(), request);
    }

    @PutMapping("/update/by/{courseId}")
    @Operation(summary = "update course", description = "we can update course")
    public CourseResponse update(@PathVariable Long courseId,
                                 @RequestBody CourseRequest courseRequest) {
        return service.update(courseId, courseRequest);
    }

    @GetMapping("/find/by/{courseId}")
    @Operation(summary = "get course by id", description = "we can get course by id")
    public CourseResponse findById(@PathVariable Long courseId) {
        return service.findById(courseId);
    }

    @DeleteMapping("/delete/by/{courseId}")
    @Operation(summary = "delete course", description = "we can delete course")
    public ResponseDelete delete(@PathVariable Long courseId) {
        return service.deleteById(courseId);
    }

    @GetMapping
    @Operation(summary = "get all course", description = "we can get all course")
    public List<CourseResponse> getAllCourse() {
        return service.getAllCourse();
    }

    @GetMapping("/pagination")
    public CourseResponseView getAllCourses(@RequestParam(name = "text", required = false) String text,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return service.getAllCoursesPagination(text, page, size);
    }
}
