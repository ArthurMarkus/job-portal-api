package com.arthurmarkus.jobportal.repository;

import com.arthurmarkus.jobportal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
