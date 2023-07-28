package com.cm.sphere.config;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.cm.sphere.model.security.AuthUserDetails;
import com.cm.sphere.service.UserAuthService;

public class JwtAuthProvider implements AuthenticationProvider {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserAuthService userAuthService;

    public JwtAuthProvider(JwtTokenUtil jwtTokenUtil, UserAuthService userAuthService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userAuthService = userAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final JwtAuthToken jwtAuthToken = (JwtAuthToken) authentication;
        final String token = (String) jwtAuthToken.getCredentials();

        final String id = jwtTokenUtil.getIdFromToken(token, 1);

        final AuthUserDetails userDetails = (AuthUserDetails) userAuthService.loadUserByUsername(id);

        jwtTokenUtil.validateToken(token, 1);

        final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        final Authentication finalAuthentication = new JwtAuthToken(userDetails, token, authorities);

        return finalAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.isAssignableFrom(authentication);
    }
}
