package com.arthurmarkus.jobportal.contact.service.impl;

import com.arthurmarkus.jobportal.contact.service.IContactService;
import com.arthurmarkus.jobportal.dto.ContactRequestDTO;
import com.arthurmarkus.jobportal.entity.Contact;
import com.arthurmarkus.jobportal.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDTO contactRequestDTO) {
        Contact contact = contactRepository.save(transformToEntity(contactRequestDTO));
        return contact != null && contact.getId() != null;
    }

    private Contact transformToEntity(ContactRequestDTO contactRequestDTO){
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDTO, contact);
        contact.setCreatedAt(Instant.now());
        contact.setCreatedBy("System");
        contact.setStatus("NEW");
        return contact;
    }
}
