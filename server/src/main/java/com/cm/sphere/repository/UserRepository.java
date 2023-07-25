package com.cm.sphere.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cm.sphere.model.Security.AuthUser;
import com.cm.sphere.model.User.BasicUserData;

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
        query.fields().include("firstName").include("lastName").include("about")
            .include("interests.mainInterest").include("assets.profilePictureUrl")
            .include("assets.bannerPictureUrl").include("verification.validated")
            .include("verification.verified");

        final BasicUserData basicUserData = this.mongoTemplate.findOne(query, BasicUserData.class);
        return basicUserData;
    }
}
