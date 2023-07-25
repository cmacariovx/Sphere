package com.cm.sphere.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.sphere.model.Request.SignupRequest;
import com.cm.sphere.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.time.Duration;
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
        final Map<String, String> tokens = this.authService.signup(body);

        final Cookie cookie = new Cookie("refresh_token", tokens.get("refresh_token"));
        cookie.setHttpOnly(true);
        // cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.ofDays(14).getSeconds());
        cookie.setAttribute("SameSite", "Lax");

        response.addCookie(cookie);

        tokens.remove("refresh_token");
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }
}
