package com.cm.sphere.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cm.sphere.exception.UserAlreadyExistsException;
import com.cm.sphere.model.Security.AuthUser;
import com.cm.sphere.model.User.User;

@Repository
public class AuthRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AuthRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public AuthUser signup(User user) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(user.getEmail()));

        if (this.mongoTemplate.exists(query, AuthUser.class)) {
            throw new UserAlreadyExistsException();
        }

        final User savedUser = this.mongoTemplate.save(user);
        return new AuthUser(savedUser.getId(), savedUser.getHashedPassword(), savedUser.getRoles());
    }
}
