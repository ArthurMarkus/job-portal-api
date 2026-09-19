package com.arthurmarkus.jobportal.scopes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
@RequiredArgsConstructor
public class ScopeController {

    private final RequestScopedBean requestScopedBean;
    private final SessionScopedBean sessionScopedBean;

    @GetMapping("/request")
    public ResponseEntity<String> testRequestScope(){
        requestScopedBean.setUsername("John Doe");
        return ResponseEntity.ok().body(requestScopedBean.getUsername());
    }

    @GetMapping("/session")
    public ResponseEntity<String> testSessionScope(){
        sessionScopedBean.setUsername("John Doe");
        return ResponseEntity.ok().body(sessionScopedBean.getUsername());
    }

    @GetMapping("/test")
    public ResponseEntity<String> testScope(){

        return ResponseEntity.ok().body(requestScopedBean.getUsername());
    }
}
