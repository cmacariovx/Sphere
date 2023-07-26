package com.cm.sphere.model.User;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class Posts {
    @Field(name = "postIds")
    private ArrayList<ObjectId> postIds;

    @Field(name = "totalUpvotes")
    private int totalUpvotes;

    @Field(name = "totalDownvotes")
    private int totalDownvotes;

    @Field(name = "totalComments")
    private int totalComments;

    @Field(name = "totalShares")
    private int totalShares;

    public Posts() {
        this.postIds = new ArrayList<>();
        this.totalUpvotes = 0;
        this.totalDownvotes = 0;
        this.totalComments = 0;
        this.totalShares = 0;
    }

    public ArrayList<ObjectId> getPostIds() {
        return this.postIds;
    }

    public int getTotalUpvotes() {
        return this.totalUpvotes;
    }

    public int getTotalDownvotes() {
        return this.totalDownvotes;
    }

    public int getTotalComments() {
        return this.totalComments;
    }

    public int getTotalShares() {
        return this.totalShares;
    }

    public void setPostIds(ArrayList<ObjectId> postIds) {
        this.postIds = postIds;
    }

    public void setTotalUpvotes(int totalUpvotes) {
        this.totalUpvotes = totalUpvotes;
    }

    public void setTotalDownvotes(int totalDownvotes) {
        this.totalDownvotes = totalDownvotes;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }

    public void addPostId(ObjectId id) {
        this.postIds.add(id);
    }

    public void incrementTotalUpvotes(int increment) {
        this.totalUpvotes += increment;
    }

    public void incrementTotalDownvotes(int increment) {
        this.totalDownvotes += increment;
    }

    public void incrementTotalComments(int increment) {
        this.totalComments += increment;
    }

    public void incrementTotalShares(int increment) {
        this.totalShares += increment;
    }
}
