package com.cm.sphere.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginRequest {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
