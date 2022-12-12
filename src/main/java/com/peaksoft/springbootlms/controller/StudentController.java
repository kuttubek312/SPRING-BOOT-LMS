package com.peaksoft.springbootlms.controller;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.student_dto.StudentRequest;
import com.peaksoft.springbootlms.dto.student_dto.StudentResponse;
import com.peaksoft.springbootlms.dto.student_dto.StudentResponseView;
import com.peaksoft.springbootlms.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@Tag(name = "Student API",
        description = "User with role admin,editor can create, update, delete or get all students ")
public class StudentController {

    private final StudentService service;

    @PostMapping("save")
    @Operation(summary = "create student", description = "we can create student")
    public StudentResponse create(@RequestBody StudentRequest request) {
        return service.create(request);
    }

    @PutMapping("/update/by/{studentId}")
    @Operation(summary = "update student", description = "we can update student")
    public StudentResponse update(@PathVariable Long studentId,
                                  @RequestBody StudentRequest studentRequest) {
        return service.update(studentId, studentRequest);
    }

    @GetMapping("/find/by/{studentId}")
    @Operation(summary = "get student by id", description = "we can get student by id")
    public StudentResponse findById(@PathVariable Long studentId) {
        return service.findById(studentId);
    }

    @DeleteMapping("/delete/by/{studentId}")
    @Operation(summary = "delete student", description = "we can delete student")
    public ResponseDelete delete(@PathVariable Long studentId) {
        return service.deleteById(studentId);
    }

    @GetMapping
    @Operation(summary = "get all students", description = "we can get all students")
    public List<StudentResponse> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/pagination")
    public StudentResponseView getAllStudents(@RequestParam(name = "text", required = false) String text,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return service.getAllStudentsPagination(text, page, size);
    }
}
