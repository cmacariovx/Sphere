package com.cm.sphere.repository;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AuthRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Map<String, Object> signup(Map<String, Object> data) {
        try {
            Map<String, Object> result = this.mongoTemplate.insert(data, "users");
            return result;
        }
        catch (Exception err) {
            System.out.println("Auth Repository: Signup: " + err.getMessage());
            throw new RuntimeException("Could not signup.", err);
        }
    }
}
