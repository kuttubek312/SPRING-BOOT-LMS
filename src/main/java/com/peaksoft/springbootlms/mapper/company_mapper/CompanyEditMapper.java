package com.peaksoft.springbootlms.mapper.company_mapper;

import com.peaksoft.springbootlms.dto.company_dto.CompanyRequest;
import com.peaksoft.springbootlms.model.Company;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CompanyEditMapper {

    public Company create(CompanyRequest request) {
        if (request == null) {
            return null;
        }
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setCreated(LocalDate.now());
        company.setActive(true);
        return company;
    }

    public void update(Company company, CompanyRequest companyRequest) {
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
    }
}
