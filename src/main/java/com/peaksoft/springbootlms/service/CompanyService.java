package com.peaksoft.springbootlms.service;

import com.peaksoft.springbootlms.dto.ResponseDelete;
import com.peaksoft.springbootlms.dto.company_dto.CompanyRequest;
import com.peaksoft.springbootlms.dto.company_dto.CompanyResponse;
import com.peaksoft.springbootlms.dto.company_dto.CompanyResponseView;
import com.peaksoft.springbootlms.exception.not_found.CompanyNotFoundException;
import com.peaksoft.springbootlms.mapper.company_mapper.CompanyEditMapper;
import com.peaksoft.springbootlms.mapper.company_mapper.CompanyViewMapper;
import com.peaksoft.springbootlms.model.Company;
import org.springframework.data.domain.Pageable;
import com.peaksoft.springbootlms.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyEditMapper editMapper;
    private final CompanyViewMapper viewMapper;

    public CompanyResponse create(CompanyRequest companyRequest) {
        Company company = editMapper.create(companyRequest);
        repository.save(company);
        return viewMapper.viewCompany(company);
    }

    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        editMapper.update(company, companyRequest);
        repository.save(company);
        return viewMapper.viewCompany(repository.save(company));
    }

    public CompanyResponse findById(Long id) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        return viewMapper.viewCompany(company);
    }

    public ResponseDelete deleteById(Long id) {
        boolean exist = repository.existsById(id);
        if (!exist) {
            throw new CompanyNotFoundException(id);
        }
        repository.deleteById(id);
        return new ResponseDelete("DELETED", "Successfully deleted company");
    }

    public List<CompanyResponse> getAllCompany() {
        return viewMapper.view(repository.findAll());
    }

    public CompanyResponseView getAllCompaniesPagination(String text, int page, int size) {
        CompanyResponseView responseView = new CompanyResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<CompanyResponse> view(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(viewMapper.viewCompany(company));
        }
        return responses;
    }

    public List<Company> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return repository.searchAndPagination(text.toUpperCase(), pageable);
    }
}
