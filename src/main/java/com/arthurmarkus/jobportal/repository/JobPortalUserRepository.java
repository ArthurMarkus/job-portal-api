package com.arthurmarkus.jobportal.repository;

import com.arthurmarkus.jobportal.entity.JobPortalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPortalUserRepository extends JpaRepository<JobPortalUser, Long> {
}
