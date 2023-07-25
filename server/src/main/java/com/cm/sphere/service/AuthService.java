package com.cm.sphere.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cm.sphere.config.JwtTokenUtil;
import com.cm.sphere.model.Request.SignupRequest;
import com.cm.sphere.model.Security.AuthUser;
import com.cm.sphere.model.User.User;
import com.cm.sphere.repository.AuthRepository;

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

    public Map<String, String> signup(SignupRequest request) {
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String email = request.getEmail();
        final String password = request.getPassword();
        final String hashedPassword = passwordEncoder.encode(password);

        final User newUser = new User(firstName, lastName, email, hashedPassword);
        final AuthUser authUser = authRepository.signup(newUser);

        final String refreshToken = jwtTokenUtil.generateToken(authUser.getStringId(), 0);
        final String accessToken = jwtTokenUtil.generateToken(authUser.getStringId(), 1);

        final Map<String, String> response = new HashMap<>();
        response.put("message", "Signup successful.");
        response.put("refresh_token", refreshToken);
        response.put("access_token", accessToken);

        return response;
    }
}
