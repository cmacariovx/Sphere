package com.cm.sphere.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cm.sphere.model.Security.AuthUser;
import com.cm.sphere.model.User.BasicUserData;
import com.cm.sphere.model.User.User;

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

        // optimize fetching instead of whole user object
        final User user = this.mongoTemplate.findOne(query, User.class);

        if (user == null) return null; // handle this case accordingly

        return new BasicUserData(
            user.getFirstName(),
            user.getLastName(),
            user.getAbout(),
            user.getInterests().getMainInterest(),
            user.getAssets().getProfilePictureUrl(),
            user.getAssets().getBannerPictureUrl(),
            user.getVerification().isValidated(),
            user.getVerification().isVerified()
        );
    }
}
