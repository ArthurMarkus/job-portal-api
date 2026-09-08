package com.arthurmarkus.jobportal.service.impl;

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
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
