package com.cm.sphere.model.main;

import java.util.ArrayList;

import com.cm.sphere.model.user.Posts;

public class MainUserActivity {
    private final Posts posts;
    private final ArrayList<String> upvotedIds;
    private final ArrayList<String> commentIds;

    public MainUserActivity(Posts posts, ArrayList<String> upvotedIds, ArrayList<String> commentIds) {
        this.posts = posts;
        this.upvotedIds = upvotedIds;
        this.commentIds = commentIds;
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
}
