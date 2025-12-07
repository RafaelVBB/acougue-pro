package com.rafael.acougue.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private Instant expiresAt;
    private String tokenType;
}
