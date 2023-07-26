package com.cm.sphere.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class Interests {
    @Field(name = "mainInterest")
    private String mainInterest;

    @Field(name = "interestsCount")
    private HashMap<String, Integer> interestsCount;

    @Field(name = "topInterests")
    private ArrayList<String> topInterests;

    @Field(name = "circleIds")
    private ArrayList<ObjectId> circleIds;

    public Interests() {
        this.mainInterest = null;
        this.interestsCount = new HashMap<>();
        this.topInterests = new ArrayList<>();
        this.circleIds = new ArrayList<>();
    }

    public String getMainInterest() {
        return this.mainInterest;
    }

    public HashMap<String, Integer> getInterestsCount() {
        return this.interestsCount;
    }

    public ArrayList<String> getTopInterests() {
        return this.topInterests;
    }

    public ArrayList<ObjectId> getCircleIds() {
        return this.circleIds;
    }

    public void setMainInterest(String mainInterest) {
        this.mainInterest = mainInterest;
    }

    public void setInterestsCount(HashMap<String, Integer> interestsCount) {
        this.interestsCount = interestsCount;
    }

    public void setTopInterests(ArrayList<String> topInterests) {
        this.topInterests = topInterests;
    }

    public void setCircleIds(ArrayList<ObjectId> circleIds) {
        this.circleIds = circleIds;
    }

    public boolean updateInterestCount(String hashtag) {
        if (hashtag == null) return false;
        if (!this.interestsCount.containsKey(hashtag)) this.interestsCount.put(hashtag, 0);

        final int updatedCount = this.interestsCount.get(hashtag) + 1;
        this.interestsCount.put(hashtag, updatedCount);
        this.updateTopInterests(hashtag, updatedCount);
        return true;
    }

    private void updateTopInterests(String hashtag, int updatedCount) {
        if (this.topInterests.size() < 10 || updatedCount > this.interestsCount.get(this.topInterests.get(topInterests.size() - 1))) {
            if (this.topInterests.size() < 10) this.topInterests.add(hashtag);
            else this.topInterests.set(this.topInterests.size() - 1, hashtag);

            if (this.topInterests.size() >= 2) {
                this.topInterests.sort((a, b) -> this.interestsCount.get(a) - this.interestsCount.get(b));
            }
        }
    }
}
