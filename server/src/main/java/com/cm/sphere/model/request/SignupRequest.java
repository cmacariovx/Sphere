package com.cm.sphere.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private final String firstName;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private final String lastName;

    @NotEmpty
    @NotNull
    @Email
    private final String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 40)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$")
    private final String password;

    public SignupRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
