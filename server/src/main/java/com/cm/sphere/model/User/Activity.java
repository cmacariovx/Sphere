package com.cm.sphere.model.User;

import java.time.Instant;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class Activity {
    @Field(name = "posts")
    private Posts posts;

    @Field(name = "upvotedIds")
    private ArrayList<ObjectId> upvotedIds;

    @Field(name = "commentIds")
    private ArrayList<ObjectId> commentIds;

    @Field(name = "lastLogin")
    private Instant lastLogin;

    @Field(name = "dateAccountCreated")
    private Instant dateAccountCreated;

    public Activity() {
        this.posts = new Posts();
        this.upvotedIds = new ArrayList<>();
        this.commentIds = new ArrayList<>();
        this.lastLogin = null;
        this.dateAccountCreated = null;
    }

    public Posts getPosts() {
        return this.posts;
    }

    public ArrayList<ObjectId> getUpvoted() {
        return this.upvotedIds;
    }

    public ArrayList<ObjectId> getComments() {
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

    public void setUpvotedIds(ArrayList<ObjectId> upvotedIds) {
        this.upvotedIds = upvotedIds;
    }

    public void setCommentIds(ArrayList<ObjectId> commentIds) {
        this.commentIds = commentIds;
    }

    public void addUpvotedId(ObjectId upvotedId) {
        this.upvotedIds.add(upvotedId);
    }

    public void addCommentId(ObjectId commentId) {
        this.commentIds.add(commentId);
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setDateAccountCreated(Instant dateAccountCreated) {
        this.dateAccountCreated = dateAccountCreated;
    }
}
