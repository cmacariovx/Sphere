package com.cm.sphere.repository;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.cm.sphere.exception.TamperedJwtException;
import com.cm.sphere.exception.UserNotFoundException;
import com.cm.sphere.model.main.MainUserActivity;
import com.cm.sphere.model.main.MainUserData;
import com.cm.sphere.model.main.MainUserVerification;
import com.cm.sphere.model.security.AuthUser;
import com.cm.sphere.model.user.Activity;
import com.cm.sphere.model.user.BasicUserData;
import com.cm.sphere.model.user.User;
import com.cm.sphere.model.user.Verification;

@Repository
public class UserRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public AuthUser findUserById(String userId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        query.fields().include("_id").include("hashedPassword").include("roles");
        return this.mongoTemplate.findOne(query, AuthUser.class);
    }

    public BasicUserData fetchBasicUserData(String userId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));

        final Update update = new Update();
        update.set("activity.lastLogin", Instant.now());

        final User user = this.mongoTemplate.findAndModify(query, update, User.class);

        if (user == null) throw new TamperedJwtException();

        return new BasicUserData(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getTitle(),
            user.getInterests().getMainInterest(),
            user.getAssets().getProfilePictureUrl(),
            user.getAssets().getBannerPictureUrl(),
            user.getVerification().getValidated(),
            user.getVerification().getVerified()
        );
    }

    public MainUserData fetchProfileData(String profileId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(profileId));

        final User user = this.mongoTemplate.findOne(query, User.class);

        if (user == null) throw new UserNotFoundException(profileId);

        final Activity userActivity = user.getActivity();
        final Verification userVerification = user.getVerification();

        final MainUserActivity mainUserActivity = new MainUserActivity(
            userActivity.getPosts(),
            userActivity.getUpvoted(),
            userActivity.getComments()
        );

        final MainUserVerification mainUserVerification = new MainUserVerification(
            userVerification.getValidated(),
            userVerification.getVerified()
        );

        final MainUserData mainUserData = new MainUserData(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getTitle(),
            user.getAbout(),
            user.getAssets(),
            mainUserActivity,
            mainUserVerification,
            user.getInterests()
        );

        return mainUserData;
    }
}
