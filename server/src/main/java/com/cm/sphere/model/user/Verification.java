package com.cm.sphere.model.user;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Field;

public class Verification {
    @Field(name = "validated")
    private boolean validated;

    @Field(name = "verified")
    private boolean verified;

    @Field(name = "dateValidated")
    private Instant dateValidated;

    @Field(name = "dateVerified")
    private Instant dateVerified;

    public Verification() {
        this.validated = false;
        this.verified = false;
        this.dateValidated = null;
        this.dateVerified = null;
    }

    public boolean isValidated() {
        return this.validated;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public Instant getDateValidated() {
        return this.dateValidated;
    }

    public Instant getDateVerified() {
        return this.dateVerified;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setDateValidated(Instant dateValidated) {
        this.dateValidated = dateValidated;
    }

    public void setDateVerifited(Instant dateVerified) {
        this.dateVerified = dateVerified;
    }
}
