package com.cm.sphere.model.user;

import java.time.Instant;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Field;

public class Activity {
    @Field(name = "posts")
    private Posts posts;

    @Field(name = "upvotedIds")
    private ArrayList<String> upvotedIds;

    @Field(name = "commentIds")
    private ArrayList<String> commentIds;

    @Field(name = "lastLogin")
    private Instant lastLogin;

    @Field(name = "dateAccountCreated")
    private Instant dateAccountCreated;

    public Activity() {
        this.posts = new Posts();
        this.upvotedIds = new ArrayList<>();
        this.commentIds = new ArrayList<>();
        this.lastLogin = Instant.now();
        this.dateAccountCreated = Instant.now();
    }

    public Posts getPosts() {
        return this.posts;
    }

    public ArrayList<String> getUpvoted() {
        return this.upvotedIds;
    }

    public ArrayList<String> getComments() {
        return this.commentIds;
    }

    public Instant getLastLogin() {
        return this.lastLogin;
    }

    public Instant getDateAccountCreated() {
        return this.dateAccountCreated;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public void setUpvotedIds(ArrayList<String> upvotedIds) {
        this.upvotedIds = upvotedIds;
    }

    public void setCommentIds(ArrayList<String> commentIds) {
        this.commentIds = commentIds;
    }

    public void addUpvotedId(String upvotedId) {
        this.upvotedIds.add(upvotedId);
    }

    public void addCommentId(String commentId) {
        this.commentIds.add(commentId);
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setDateAccountCreated(Instant dateAccountCreated) {
        this.dateAccountCreated = dateAccountCreated;
    }
}
