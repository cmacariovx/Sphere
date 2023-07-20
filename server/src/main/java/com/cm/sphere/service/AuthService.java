package com.cm.sphere.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cm.sphere.repository.AuthRepository;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Map<String, Object> signup(Map<String, Object> data) {
        try {
            return this.authRepository.signup(data);
        }
        catch (Exception err) {
            System.out.println("Auth Service - Signup: " + err.getMessage());
            throw new RuntimeException("Could not signup.", err);
        }
    }
}
