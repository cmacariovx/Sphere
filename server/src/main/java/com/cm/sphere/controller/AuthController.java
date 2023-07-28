package com.cm.sphere.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.sphere.model.request.LoginRequest;
import com.cm.sphere.model.request.SignupRequest;
import com.cm.sphere.model.response.AuthResponse;
import com.cm.sphere.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupRequest body, HttpServletResponse response) {
        final AuthResponse authResponse = this.authService.signup(body);

        final Cookie cookie = this.authService.createRefreshTokenCookie(authResponse.getRefreshToken());
        response.addCookie(cookie);

        authResponse.setRefreshToken(null);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest body, HttpServletResponse response) {
        final AuthResponse authResponse = this.authService.login(body);

        final Cookie cookie = this.authService.createRefreshTokenCookie(authResponse.getRefreshToken());
        response.addCookie(cookie);

        authResponse.setRefreshToken(null);;
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        final Map<String, String> message = new HashMap<>();
        message.put("message", "Logged out successfully.");
        final Cookie cookie = this.authService.createExpiredRefreshTokenCookie();
        response.addCookie(cookie);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
