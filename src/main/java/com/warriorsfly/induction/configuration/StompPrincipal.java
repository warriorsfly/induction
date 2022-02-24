package com.warriorsfly.induction.configuration;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.HashSet;

public class StompPrincipal implements Principal {
    private final String username;

    public StompPrincipal(String username){
        this.username=username;
    }



    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
