package com.arthurmarkus.jobportal.company.controller;

import com.arthurmarkus.jobportal.dto.CompanyDTO;
import com.arthurmarkus.jobportal.company.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;

    @GetMapping(version = "1.0")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(){
        List<CompanyDTO> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyList);
    }
}
