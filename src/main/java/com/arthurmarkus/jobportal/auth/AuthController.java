package com.arthurmarkus.jobportal.auth;

import com.arthurmarkus.jobportal.constants.ApplicationConstants;
import com.arthurmarkus.jobportal.dto.LoginRequestDTO;
import com.arthurmarkus.jobportal.dto.LoginResponseDto;
import com.arthurmarkus.jobportal.dto.RegisterRequestDto;
import com.arthurmarkus.jobportal.dto.UserDTO;
import com.arthurmarkus.jobportal.entity.JobPortalUser;
import com.arthurmarkus.jobportal.entity.Role;
import com.arthurmarkus.jobportal.repository.JobPortalUserRepository;
import com.arthurmarkus.jobportal.repository.RoleRepository;
import com.arthurmarkus.jobportal.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final JobPortalUserRepository jobPortalUserRepository;
    private final RoleRepository roleRepository;
    private final CompromisedPasswordChecker compromisedPasswordChecker;

    @PostMapping(value = "/login/public", version = "1.0")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Authentication resultAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password()));

            // generate jwt
            String jwtToken = jwtUtil.generateJwtToken(resultAuthentication);

            UserDTO userDTO = new UserDTO();

            return ResponseEntity.status(HttpStatus.OK).body( new LoginResponseDto( HttpStatus.OK.getReasonPhrase(), userDTO, jwtToken) );

        } catch (BadCredentialsException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        } catch (AuthenticationException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication fialed");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    @PostMapping(value = "/register/public", version = "1.0")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto){

        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDto.password());
        if (decision.isCompromised()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("password", "Choose a strong password"));
        }

        Optional<JobPortalUser> existingUser = jobPortalUserRepository.readUserByEmailOrMobileNumber(registerRequestDto.email(), registerRequestDto.mobileNumber());

        if (existingUser.isPresent()){
            Map<String, String> errors = new HashMap<>();
            JobPortalUser jobPortalUser = existingUser.get();

            if (jobPortalUser.getEmail().equalsIgnoreCase(registerRequestDto.email())){
                errors.put("email", "Email is already registered");
            }
            if (jobPortalUser.getMobileNumber().equals(registerRequestDto.mobileNumber())){
                errors.put("mobileNumber", "Mobile number is already registered");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        JobPortalUser jobPortalUser= new JobPortalUser();
        BeanUtils.copyProperties(registerRequestDto, jobPortalUser);
        jobPortalUser.setPasswordHash(passwordEncoder.encode(registerRequestDto.password()));
        Role role = roleRepository.findRoleByName(ApplicationConstants.ROLE_JOB_SEEKER)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + ApplicationConstants.ROLE_JOB_SEEKER));

        jobPortalUser.setRole(role);
        jobPortalUserRepository.save(jobPortalUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }
}
