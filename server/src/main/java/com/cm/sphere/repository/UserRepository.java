package com.cm.sphere.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cm.sphere.model.Security.AuthUser;

@Repository
public class UserRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public AuthUser findUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        query.fields().include("_id").include("hashedPassword").include("roles");
        return this.mongoTemplate.findOne(query, AuthUser.class);
    }
}
