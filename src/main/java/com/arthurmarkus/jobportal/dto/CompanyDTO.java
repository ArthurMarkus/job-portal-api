package com.arthurmarkus.jobportal.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CompanyDTO(Long id, String name, String logo, String industry, String size,
                         BigDecimal rating, String locations, Integer founded, String description,
                         Integer employees, String website, Instant createdAt) {
}
