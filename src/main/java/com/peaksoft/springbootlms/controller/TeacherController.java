package com.peaksoft.springbootlms.controller;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherRequest;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherResponse;
import com.peaksoft.springbootlms.dto.teacher_dto.TeacherResponseView;
import com.peaksoft.springbootlms.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@Tag(name = "Teacher API",
        description = "User with role admin,editor can create, update, delete or get all teacher ")
public class TeacherController {

    private final TeacherService service;

    @PostMapping("/save")
    @Operation(summary = "create teacher", description = "we can create teacher")
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return service.create(request);
    }

    @PutMapping("/update/by/{teacherId}")
    @Operation(summary = "update teacher", description = "we can update teacher")
    public TeacherResponse update(@PathVariable Long teacherId,
                                  @RequestBody TeacherRequest request) {
        return service.update(teacherId, request);
    }

    @GetMapping("/find/by/{teacherId}")
    @Operation(summary = "get teacher by id", description = "we can get teacher by id")
    public TeacherResponse findById(@PathVariable Long teacherId) {
        return service.findById(teacherId);
    }

    @DeleteMapping("/delete/by/{teacherId}")
    @Operation(summary = "delete teacher", description = "we can delete teacher")
    public ResponseDelete delete(@PathVariable Long teacherId) {
        return service.deleteById(teacherId);
    }

    @GetMapping
    @Operation(summary = "get all teacher", description = "we can get all teacher")
    public List<TeacherResponse> getAllTeachers() {
        return service.getAllTeachers();
    }

    @GetMapping("/pagination")
    public TeacherResponseView getAllTeachers(@RequestParam(name = "text", required = false) String text,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return service.getAllTeachersPagination(text, page, size);
    }
}
