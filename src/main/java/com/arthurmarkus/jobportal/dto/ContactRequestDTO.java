package com.arthurmarkus.jobportal.dto;

import java.io.Serializable;

public record ContactRequestDTO(String email, String message, String name,
                                String subject, String userType) implements Serializable {
}
