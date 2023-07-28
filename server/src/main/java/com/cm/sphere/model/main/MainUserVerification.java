package com.cm.sphere.model.main;

public class MainUserVerification {
    private final boolean validated;
    private final boolean verified;

    public MainUserVerification(boolean validated, boolean verified) {
        this.validated = validated;
        this.verified = verified;
    }

    public boolean getValidated() {
        return this.validated;
    }

    public boolean getVerified() {
        return this.verified;
    }
}
