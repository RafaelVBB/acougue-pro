package com.rafael.acougue.service;

import com.rafael.acougue.dto.AuthRequest;
import com.rafael.acougue.dto.AuthResponse;
import com.rafael.acougue.dto.RefreshRequest;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    AuthResponse refresh(RefreshRequest request);
}
