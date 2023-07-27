package com.cm.sphere.config;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.cm.sphere.model.security.AuthUserDetails;

public class JwtAuthToken extends AbstractAuthenticationToken {

    private AuthUserDetails principal;
    private String token;

    public JwtAuthToken(String token) {
        super(null);
        this.token = token;
    }

    public JwtAuthToken(AuthUserDetails principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
