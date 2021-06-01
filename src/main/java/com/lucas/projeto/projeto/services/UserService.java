package com.lucas.projeto.projeto.services;

import com.lucas.projeto.projeto.security.UserSS;

import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
