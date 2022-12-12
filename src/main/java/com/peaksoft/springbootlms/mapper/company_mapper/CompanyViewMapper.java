package com.peaksoft.springbootlms.mapper.company_mapper;

import com.peaksoft.springbootlms.dto.company_dto.CompanyResponse;
import com.peaksoft.springbootlms.model.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyViewMapper {

    public CompanyResponse viewCompany(Company company) {
        if (company == null) {
            return null;
        }
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setCompanyName(company.getCompanyName());
        response.setLocatedCountry(company.getLocatedCountry());
        response.setActive(company.isActive());
        response.setCreated(company.getCreated());
        return response;
    }

    public List<CompanyResponse> view(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(viewCompany(company));
        }
        return responses;
    }
}
