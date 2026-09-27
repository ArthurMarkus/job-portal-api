package com.arthurmarkus.jobportal.dto;

public record LoginResponseDto(String message, UserDTO user, String jwtToken) {
}
