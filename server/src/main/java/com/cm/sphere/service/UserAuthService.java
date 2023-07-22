package com.cm.sphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cm.sphere.model.Security.AuthUser;
import com.cm.sphere.model.Security.AuthUserDetails;
import com.cm.sphere.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthUserDetails loadUserByUsername(String userId) {
        AuthUser user = userRepository.findUserById(userId);

        if (user == null) throw new UsernameNotFoundException("User not found with id: " + userId);

        return new AuthUserDetails(user.getStringId(), user.getHashedPassword(), user.getRoles());
    }
}
