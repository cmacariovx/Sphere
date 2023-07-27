package com.cm.sphere.model.user;

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

    @Field(name = "about")
    private String about;

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
        this.about = null;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.assets = new Assets();
        this.activity = new Activity();
        this.verification = new Verification();
        this.interests = new Interests();
        this.roles = new ArrayList<>();
    }

    public ObjectId getId() {
        return this.id;
    }

    public String getStringId() {
        return this.id.toHexString();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAbout() {
        return this.about;
    }

    public String getEmail() {
        return this.email;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public Assets getAssets() {
        return this.assets;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Verification getVerification() {
        return this.verification;
    }

    public Interests getInterests() {
        return this.interests;
    }

    public ArrayList<String> getRoles() {
        return this.roles;
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

    public void setAbout (String about) {
        this.about = about;
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
