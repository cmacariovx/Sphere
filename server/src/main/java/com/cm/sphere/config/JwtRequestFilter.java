package com.cm.sphere.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cm.sphere.exception.BaseCustomAuthException;
import com.cm.sphere.exception.InvalidAccessTokenHeaderException;
import com.cm.sphere.exception.TamperedJwtException;
import com.cm.sphere.model.security.AuthUserDetails;
import com.cm.sphere.service.UserAuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserAuthService userAuthService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final AntPathMatcher pathMatcher;
    private final List<String> nonFilterUris;

    @Autowired
    public JwtRequestFilter(UserAuthService userAuthService, JwtTokenUtil jwtTokenUtil, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.userAuthService = userAuthService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.pathMatcher = new AntPathMatcher();
        this.nonFilterUris = Arrays.asList("/auth/**", "/user/fetchBasicData");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String accessTokenHeader = request.getHeader("Authorization");
            if (accessTokenHeader == null || !accessTokenHeader.startsWith("Bearer ")) throw new InvalidAccessTokenHeaderException();

            final String accessToken = accessTokenHeader.substring(7);
            final Integer tokenType = jwtTokenUtil.getClaimFromTokenWithoutValidation(accessToken, claims -> claims.get("token_type", Integer.class));
            final String userId = jwtTokenUtil.getIdFromToken(accessToken, tokenType);

            if (userId == null || tokenType == null) throw new TamperedJwtException();
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                chain.doFilter(request, response);
                return;
            }

            final AuthUserDetails userDetails = this.userAuthService.loadUserByUsername(userId);
            jwtTokenUtil.validateToken(accessToken, userDetails, tokenType);

            final JwtAuthToken jwtAuthToken = new JwtAuthToken(userDetails, accessToken, userDetails.getAuthorities());

            jwtAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(jwtAuthToken);

            chain.doFilter(request, response);
        }
        catch (final BaseCustomAuthException ex) {
            this.customAuthenticationEntryPoint.commence(request, response, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        final String requestPath = request.getServletPath().equals("") ? request.getRequestURI() : request.getServletPath();
        return nonFilterUris.stream().anyMatch(pattern -> pathMatcher.match(pattern, requestPath));
    }
}
