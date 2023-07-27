package com.cm.sphere.model.security;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class AuthUser {
    @Id
    private ObjectId id;

    @Field(name = "hashedPassword")
    private String hashedPassword;

    @Field(name = "roles")
    private ArrayList<String> roles;

    public AuthUser() {};

    public AuthUser(ObjectId id, String hashedPassword, ArrayList<String> roles) {
        this.id = id;
        this.hashedPassword = hashedPassword;
        this.roles = roles;
    }

    public ObjectId getId() {
        return this.id;
    }

    public String getStringId() {
        return this.id.toHexString();
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public ArrayList<String> getRoles() {
        return this.roles;
    }
}
