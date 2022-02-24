package com.winning.ds.veda.configuration;

import javax.security.auth.Subject;
import java.security.Principal;

public class StompPrincipal implements Principal {
    public static final String ROLE_DOCTOR = "doctor";
    public static final String ROLE_NURSE = "nurse";
    private String username;
    private String role;

    public StompPrincipal(String username, String role){
        this.username=username;
        this.role=role;
    }



    @Override
    public String getName() {
        return String.format("%s-%s", role, username);
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
