package com.cm.sphere.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cm.sphere.exception.BaseCustomAuthException;
import com.cm.sphere.exception.InvalidAccessTokenHeaderException;
import com.cm.sphere.exception.TamperedJwtException;
import com.cm.sphere.model.Security.AuthUserDetails;
import com.cm.sphere.service.UserAuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserAuthService userAuthService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    public JwtRequestFilter(UserAuthService userAuthService, JwtTokenUtil jwtTokenUtil, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.userAuthService = userAuthService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String requestURI = request.getRequestURI();

            if (requestURI.startsWith("/api/auth/")) {
                chain.doFilter(request, response);
                return;
            }

            final String accessTokenHeader = request.getHeader("Authorization");
            if (accessTokenHeader == null || !accessTokenHeader.startsWith("Bearer ")) throw new InvalidAccessTokenHeaderException();

            final String accessToken = accessTokenHeader.substring(7);
            final Integer tokenType = jwtTokenUtil.getClaimFromTokenWithoutValidation(accessToken, claims -> claims.get("token_type", Integer.class));
            final String id = jwtTokenUtil.getIdFromToken(accessToken, tokenType);

            if (id == null || tokenType == null) throw new TamperedJwtException();
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                chain.doFilter(request, response);
                return;
            }

            final AuthUserDetails userDetails = this.userAuthService.loadUserByUsername(id);
            jwtTokenUtil.validateToken(accessToken, userDetails, tokenType);

            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken (userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            chain.doFilter(request, response);
        }
        catch (final BaseCustomAuthException ex) {
            this.customAuthenticationEntryPoint.commence(request, response, ex);
            return;
        }
    }
}
