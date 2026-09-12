package com.arthurmarkus.jobportal.contact.service;

import com.arthurmarkus.jobportal.dto.ContactRequestDTO;

public interface IContactService {

    boolean saveContact(ContactRequestDTO contactRequestDTO);
}
