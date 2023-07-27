package com.cm.sphere.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cm.sphere.exception.UserAlreadyExistsException;
import com.cm.sphere.model.request.LoginRequest;
import com.cm.sphere.model.user.BasicUserData;
import com.cm.sphere.model.user.User;

@Repository
public class AuthRepository {
    private final MongoTemplate mongoTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthRepository(MongoTemplate mongoTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.mongoTemplate = mongoTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public BasicUserData signup(User user) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(user.getEmail()));

        if (this.mongoTemplate.exists(query, User.class)) throw new UserAlreadyExistsException();

        final User savedUser = this.mongoTemplate.save(user);

        return new BasicUserData(
            savedUser.getId().toHexString(),
            savedUser.getFirstName(),
            savedUser.getLastName(),
            savedUser.getAbout(),
            savedUser.getInterests().getMainInterest(),
            savedUser.getAssets().getProfilePictureUrl(),
            savedUser.getAssets().getBannerPictureUrl(),
            savedUser.getVerification().isValidated(),
            savedUser.getVerification().isVerified()
        );
    }

    public BasicUserData login(LoginRequest request) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(request.getEmail()));
        final User user = mongoTemplate.findOne(query, User.class);

        if (user == null) throw new BadCredentialsException("Invalid credentials");

        final boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getHashedPassword());

        if (!passwordMatches) throw new BadCredentialsException("Invalid credentials.");

        return new BasicUserData(
            user.getId().toHexString(),
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
