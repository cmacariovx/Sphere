package com.cm.sphere.model.User;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    @Field(name = "firstName")
    private String firstName;

    @Field(name = "lastName")
    private String lastName;

    @Field(name = "email")
    private String email;

    @Field(name = "hashedPassword")
    private String hashedPassword;

    @Field(name = "assets")
    private Assets assets;

    @Field(name = "activity")
    private Activity activity;

    @Field(name = "verification")
    private Verification verification;

    @Field(name = "interests")
    private Interests interests;

    @Field(name = "roles")
    private ArrayList<String> roles;

    public User(String firstName, String lastName, String email, String hashedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.assets = new Assets();
        this.activity = new Activity();
        this.verification = new Verification();
        this.interests = new Interests();
        this.roles = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public String getStringId() {
        return id.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public Assets getAssets() {
        return assets;
    }

    public Activity getActivity() {
        return activity;
    }

    public Verification getVerification() {
        return verification;
    }

    public Interests getInterests() {
        return interests;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }
}
