package com.cm.sphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.sphere.config.JwtTokenUtil;
import com.cm.sphere.exception.MissingRefreshTokenException;
import com.cm.sphere.model.Response.BasicUserDataAndToken;
import com.cm.sphere.model.User.BasicUserData;
import com.cm.sphere.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public BasicUserDataAndToken fetchBasicUserData(HttpServletRequest request) {
        String refreshToken = null;
        final Cookie[] cookies = request.getCookies();

        if (cookies == null) throw new MissingRefreshTokenException();

        for (final Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh_token")) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null) throw new MissingRefreshTokenException();

        final String userId = this.jwtTokenUtil.getIdFromToken(refreshToken, 0);
        final String newAccessToken = this.jwtTokenUtil.generateToken(userId, 1);
        final BasicUserData basicUserData = this.userRepository.fetchBasicUserData(userId);

        return new BasicUserDataAndToken(newAccessToken, basicUserData);
    }
}
