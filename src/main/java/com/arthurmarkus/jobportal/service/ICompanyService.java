package com.arthurmarkus.jobportal.service;

import com.arthurmarkus.jobportal.dto.CompanyDTO;
import com.arthurmarkus.jobportal.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<CompanyDTO> getAllCompanies();
}
