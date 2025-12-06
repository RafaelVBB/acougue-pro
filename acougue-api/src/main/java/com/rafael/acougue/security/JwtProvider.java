package com.rafael.acougue.security;

import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    public String gerarTokenPlaceholder() {
        return "token";
    }
}
