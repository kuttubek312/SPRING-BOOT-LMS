package com.peaksoft.springbootlms.controller;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.group_dto.GroupRequest;
import com.peaksoft.springbootlms.dto.group_dto.GroupResponse;
import com.peaksoft.springbootlms.dto.group_dto.GroupResponseView;
import com.peaksoft.springbootlms.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Group API",
        description = "User with role admin,editor can create, update, delete or get all group ")
public class GroupController {

    private final GroupService service;

    @PostMapping("/save")
    @Operation(summary = "create group", description = "we can create group")
    public GroupResponse create(@RequestBody GroupRequest request) {
        return service.create(request);
    }

    @PutMapping("/update/by/{groupId}")
    @Operation(summary = "update group", description = "we can update group")
    public GroupResponse update(@PathVariable Long groupId,
                                @RequestBody GroupRequest groupRequest){
        return service.update(groupId,groupRequest);
    }


    @GetMapping("/find/by/{groupId}")
    @Operation(summary = "get group by id", description = "we can get group by id")
    public GroupResponse findById(@PathVariable Long groupId) {
        return service.findById(groupId);
    }

    @DeleteMapping("/delete/by/{groupId}")
    @Operation(summary = "delete group", description = "we can delete group")
    public ResponseDelete delete(@PathVariable Long groupId) {
        return service.deleteById(groupId);
    }

    @GetMapping
    @Operation(summary = "get all group", description = "we can get all group")
    public List<GroupResponse> getAllGroups() {
        return service.getAllCourse();
    }

    @GetMapping("/pagination")
    public GroupResponseView getAllGroup(@RequestParam(name = "text", required = false) String text,
                                         @RequestParam int page,
                                         @RequestParam int size) {
        return service.getAllGroupPagination(text, page, size);
    }
}
