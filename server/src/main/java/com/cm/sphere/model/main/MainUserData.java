package com.cm.sphere.model.main;

import com.cm.sphere.model.user.Assets;
import com.cm.sphere.model.user.Interests;

public class MainUserData {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String title;
    private final String about;
    private final Assets assets;
    private final MainUserActivity activity;
    private final MainUserVerification verification;
    private final Interests interests;

    public MainUserData(String id, String firstName, String lastName, String title, String about, Assets assets, MainUserActivity activity, MainUserVerification verification, Interests interests) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.about = about;
        this.assets = assets;
        this.activity = activity;
        this.verification = verification;
        this.interests = interests;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAbout() {
        return this.about;
    }

    public Assets getAssets() {
        return this.assets;
    }

    public MainUserActivity getActivity() {
        return this.activity;
    }

    public MainUserVerification getVerification() {
        return this.verification;
    }

    public Interests getInterests() {
        return this.interests;
    }
}
