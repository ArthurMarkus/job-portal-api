package com.arthurmarkus.jobportal.repository;

import com.arthurmarkus.jobportal.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
