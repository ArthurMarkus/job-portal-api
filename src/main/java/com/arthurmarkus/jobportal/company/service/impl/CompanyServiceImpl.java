package com.arthurmarkus.jobportal.company.service.impl;

import com.arthurmarkus.jobportal.dto.CompanyDTO;
import com.arthurmarkus.jobportal.dto.JobDTO;
import com.arthurmarkus.jobportal.entity.Company;
import com.arthurmarkus.jobportal.entity.Job;
import com.arthurmarkus.jobportal.repository.CompanyRepository;
import com.arthurmarkus.jobportal.company.service.ICompanyService;
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
        return companyList.stream().map(this::transformCompanyToDTO).toList();
    }

    private CompanyDTO transformCompanyToDTO(Company company){
        List<JobDTO> jobDTOS = company.getJobs().stream().map(this::transformJobToDTO).toList();
        return new CompanyDTO(company.getId(), company.getName(), company.getLogo(), company.getIndustry(),
                company.getSize(), company.getRating(), company.getLocations(), company.getFounded(),
                company.getDescription(), company.getEmployees(), company.getWebsite(), company.getCreatedAt(), jobDTOS);
    }

    private JobDTO transformJobToDTO(Job job){
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getDescription(),
                job.getRequirements(),
                job.getBenefits(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }
}
