package com.arthurmarkus.jobportal.repository;

import com.arthurmarkus.jobportal.entity.JobPortalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPortalUserRepository extends JpaRepository<JobPortalUser, Long> {

    Optional<JobPortalUser> readUserByEmailOrMobileNumber(String email, String mobileNumber);
}
