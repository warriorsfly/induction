package com.warriorsfly.col.configuration.web;

import javax.security.auth.Subject;
import java.security.Principal;

public class ColPrincipal implements Principal {
    private final String username;
    public ColPrincipal(String username){
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
