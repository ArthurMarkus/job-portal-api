package com.arthurmarkus.jobportal.repository;

import com.arthurmarkus.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // optional
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
