package com.arthurmarkus.jobportal.service.impl;

import com.arthurmarkus.jobportal.dto.CompanyDTO;
import com.arthurmarkus.jobportal.entity.Company;
import com.arthurmarkus.jobportal.repository.CompanyRepository;
import com.arthurmarkus.jobportal.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(this::transformToDTO).toList();
    }

    private CompanyDTO transformToDTO(Company company){
        return new CompanyDTO(company.getId(), company.getName(), company.getLogo(), company.getIndustry(),
                company.getSize(), company.getRating(), company.getLocations(), company.getFounded(),
                company.getDescription(), company.getEmployees(), company.getWebsite(), company.getCreatedAt());
    }
}
