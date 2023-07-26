package com.cm.sphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.sphere.model.Response.BasicUserDataAndToken;
import com.cm.sphere.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/fetchBasicData")
    public ResponseEntity<Object> fetchBasicData(HttpServletRequest request) {
        final BasicUserDataAndToken basicUserDataAndToken = this.userService.fetchBasicUserData(request);
        return new ResponseEntity<>(basicUserDataAndToken, HttpStatus.OK);
    }
}
