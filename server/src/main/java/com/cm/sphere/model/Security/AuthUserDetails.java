package com.cm.sphere.model.Security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetails implements UserDetails {
    private final String id;
    private final String hashedPassword;
    private final ArrayList<String> roles;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUserDetails(String id, String hashedPassword, ArrayList<String> roles) {
        this.id = id;
        this.hashedPassword = hashedPassword;
        this.roles = roles;
        this.authorities = this.roles.stream().map(role -> new SimpleGrantedAuthority(role)).toList();
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
