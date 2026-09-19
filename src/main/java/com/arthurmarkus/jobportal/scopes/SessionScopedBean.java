package com.arthurmarkus.jobportal.scopes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Getter @Setter
public class SessionScopedBean {

    private String username;

    public SessionScopedBean(){
        System.out.println("SessionScopedBean created");
    }
}
