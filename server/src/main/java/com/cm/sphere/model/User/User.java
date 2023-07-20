package com.cm.sphere.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    @Field(name = "firstName")
    @Size(min = 2, max = 20)
    private String firstName;

    @Field(name = "lastName")
    @Size(min = 2, max = 20)
    private String lastName;

    @Field(name = "email")
    @Email
    @NotEmpty
    private String email;

    @Field(name = "hashedPassword")
    @Size(min = 8, max = 40)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$")
    private String hashedPassword;

    @Field(name = "assets")
    private Assets assets;

    @Field(name = "activity")
    private Activity activity;

    @Field(name = "verification")
    private Verification verification;

    @Field(name = "interests")
    private Interests interests;

    public User(String firstName, String lastName, String email, String hashedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.assets = new Assets();
        this.activity = new Activity();
        this.verification = new Verification();
        this.interests = new Interests();
    }
}
