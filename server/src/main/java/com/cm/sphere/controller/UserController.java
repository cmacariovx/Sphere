package com.cm.sphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.sphere.model.main.MainUserData;
import com.cm.sphere.model.request.FetchProfileDataRequest;
import com.cm.sphere.model.response.BasicUserDataAndToken;
import com.cm.sphere.service.AuthService;
import com.cm.sphere.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/fetchBasicData")
    public ResponseEntity<Object> fetchBasicData(HttpServletRequest request, HttpServletResponse response) {
        final BasicUserDataAndToken basicUserDataAndToken = this.userService.fetchBasicUserData(request);

        final Cookie cookie = this.authService.createRefreshTokenCookie(basicUserDataAndToken.getNewRefreshToken());
        response.addCookie(cookie);

        basicUserDataAndToken.setNewRefreshToken(null);
        return new ResponseEntity<>(basicUserDataAndToken, HttpStatus.OK);
    }

    @PostMapping("/fetchProfileData")
    public ResponseEntity<Object> fetchProfileData(@Valid @RequestBody FetchProfileDataRequest body) {
        final MainUserData profileData = this.userService.fetchProfileData(body.getProfileId());
        return new ResponseEntity<>(profileData, HttpStatus.OK);
    }
}
