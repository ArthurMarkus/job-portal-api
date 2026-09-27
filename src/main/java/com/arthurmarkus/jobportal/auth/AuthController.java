package com.arthurmarkus.jobportal.auth;

import com.arthurmarkus.jobportal.dto.LoginRequestDTO;
import com.arthurmarkus.jobportal.dto.LoginResponseDto;
import com.arthurmarkus.jobportal.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login/public")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserDTO userDTO = new UserDTO();
        return ResponseEntity.status(HttpStatus.OK).body( new LoginResponseDto( HttpStatus.OK.getReasonPhrase(), userDTO, null) );
    }
}
