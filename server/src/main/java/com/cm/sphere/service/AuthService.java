package com.cm.sphere.service;

import java.time.Duration;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cm.sphere.config.JwtTokenUtil;
import com.cm.sphere.model.request.LoginRequest;
import com.cm.sphere.model.request.SignupRequest;
import com.cm.sphere.model.response.AuthResponse;
import com.cm.sphere.model.user.BasicUserData;
import com.cm.sphere.model.user.User;
import com.cm.sphere.repository.AuthRepository;

import jakarta.servlet.http.Cookie;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(AuthRepository authRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public AuthResponse signup(SignupRequest request) {
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String email = request.getEmail();
        final String password = request.getPassword();
        final String hashedPassword = this.passwordEncoder.encode(password);

        final User newUser = new User(firstName, lastName, email, hashedPassword);
        final BasicUserData userData = this.authRepository.signup(newUser);

        final String refreshToken = this.jwtTokenUtil.generateToken(userData.getId(), 0);
        final String accessToken = this.jwtTokenUtil.generateToken(userData.getId(), 1);

        userData.setId(null);
        final AuthResponse response = new AuthResponse("Signup successful", accessToken, userData, refreshToken);
        return response;
    }

    public AuthResponse login(LoginRequest request) {
        final BasicUserData userData = this.authRepository.login(request);

        final String refreshToken = this.jwtTokenUtil.generateToken(userData.getId(), 0);
        final String accessToken = this.jwtTokenUtil.generateToken(userData.getId(), 1);

        userData.setId(null);
        final AuthResponse response = new AuthResponse("Login successful.", accessToken, userData, refreshToken);
        return response;
    }

    public Cookie createRefreshTokenCookie(String token) {
        final Cookie cookie = new Cookie("refresh_token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) Duration.ofDays(14).getSeconds());
        cookie.setAttribute("SameSite", "Lax");
        // cookie.setSecure(true);
        return cookie;
    }

    public Cookie createExpiredRefreshTokenCookie() {
        final Cookie cookie = new Cookie("refresh_token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }
}
