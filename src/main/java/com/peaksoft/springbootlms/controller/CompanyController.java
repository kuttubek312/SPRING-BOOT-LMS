package com.peaksoft.springbootlms.controller;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.company_dto.CompanyRequest;
import com.peaksoft.springbootlms.dto.company_dto.CompanyResponse;
import com.peaksoft.springbootlms.dto.company_dto.CompanyResponseView;
import com.peaksoft.springbootlms.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Company API",
        description = "User with role admin,editor can create, update, delete or get all company ")
public class CompanyController {

    private final CompanyService service;

    @PostMapping("/save")
    @Operation(summary = "create company", description = "we can create company")
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return service.create(request);
    }

    @PutMapping("/update/by/{companyId}")
    @Operation(summary = "update company", description = "we can update company")
    public CompanyResponse update(@PathVariable Long companyId,
                                  @RequestBody CompanyRequest request) {
        return service.update(companyId, request);
    }

    @GetMapping("/find/by/{companyId}")
    @Operation(summary = "get company by id", description = "we can get company by id")
    public CompanyResponse findById(@PathVariable Long companyId) {
        return service.findById(companyId);
    }

    @DeleteMapping("/delete/by/{companyId}")
    @Operation(summary = "delete company", description = "we can delete company")
    public ResponseDelete delete(@PathVariable Long companyId) {
        return service.deleteById(companyId);
    }

    @GetMapping()
    @Operation(summary = "get all company", description = "we can get all company")
    public List<CompanyResponse> getAllCompanies() {
        return service.getAllCompany();
    }

    @GetMapping("/pagination")
    public CompanyResponseView getAllCompanies(@RequestParam(name = "text", required = false) String text,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        return service.getAllCompaniesPagination(text, page, size);
    }
}
