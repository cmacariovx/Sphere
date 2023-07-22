package com.cm.sphere.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cm.sphere.model.Security.AuthUserDetails;
import com.cm.sphere.service.UserAuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String id = null;
        String jwtToken = null;
        Integer tokenType = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                tokenType = jwtTokenUtil.getClaimFromTokenWithoutValidation(jwtToken, claims -> claims.get("token_type", Integer.class));
                id = jwtTokenUtil.getIdFromToken(jwtToken, tokenType);
            }
            catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e) {
                logger.warn("JWT Token has expired");
                // Send the user to /refresh endpoint to refresh the JWT token here
            }
        }

        if ((id != null && tokenType != null) && SecurityContextHolder.getContext().getAuthentication() == null) {
            AuthUserDetails userDetails = this.userAuthService.loadUserByUsername(id);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails, tokenType)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken
                (userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
