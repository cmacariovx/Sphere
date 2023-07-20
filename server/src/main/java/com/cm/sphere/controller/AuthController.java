package com.cm.sphere.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.sphere.service.AuthService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody Map<String, Object> body) {
        try {
            return new ResponseEntity<Object>(this.authService.signup(body), HttpStatusCode.valueOf(200));
        }
        catch (Exception err) {
            System.out.println("Auth Controller - Signup: " + err.getMessage());
            final HashMap<String, String> error = new HashMap<>();
            error.put("error", err.getMessage());
            return new ResponseEntity<Object>(error, HttpStatus.valueOf(400));
        }
    }
}
