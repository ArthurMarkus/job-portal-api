package com.arthurmarkus.jobportal.service.impl;

import com.arthurmarkus.jobportal.entity.Company;
import com.arthurmarkus.jobportal.repository.CompanyRepository;
import com.arthurmarkus.jobportal.service.ICompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
