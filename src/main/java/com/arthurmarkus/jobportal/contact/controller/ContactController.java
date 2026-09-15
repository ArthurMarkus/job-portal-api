package com.arthurmarkus.jobportal.contact.controller;

import com.arthurmarkus.jobportal.contact.service.IContactService;
import com.arthurmarkus.jobportal.dto.ContactRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping(version = "1.0")
    public ResponseEntity<String> saveContactMsg(@RequestBody @Valid ContactRequestDTO contactRequestDTO){
        boolean isSaved = contactService.saveContact(contactRequestDTO);

        if (isSaved){
            return ResponseEntity.status(HttpStatus.CREATED).body("Request processed successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request processing failed");
    }
}
