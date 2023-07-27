package com.cm.sphere.integration.jwt;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cm.sphere.config.JwtTokenUtilTest;
import com.cm.sphere.model.Request.SignupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtIntegrationTest {
    private final MockMvc mockMvc;
    private final JwtTokenUtilTest jwtTokenUtilTest;

    private static final String testUserId = "64c1a4aaefd1ab63c198f869";
    private static final Logger logger = LoggerFactory.getLogger(JwtIntegrationTest.class);

    @Autowired
    public JwtIntegrationTest(JwtTokenUtilTest jwtTokenUtilTest, MockMvc mockMvc) {
        this.jwtTokenUtilTest = jwtTokenUtilTest;
        this.mockMvc = mockMvc;
    }

    @Test
    public void testRefreshTokenHttpCookieReceival() throws Exception {
        final SignupRequest request = new SignupRequest("John", "Doe", "john.doe@example.com", "Password123#");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.cookie().exists("refresh_token"));
    }

    @Test
    public void testFetchBasicDataWithValidToken() throws Exception {
        final String token = jwtTokenUtilTest.generateValidTestToken(JwtIntegrationTest.testUserId, 0);
        final Cookie refreshCookie = new Cookie("refresh_token", token);

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/fetchBasicData")
                .cookie(refreshCookie))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        logger.trace(result.getResponse().getContentAsString());
    }


    // Test fetching new refresh token

    // Test endpoints that require auth header and access token here with various jwt cases
}
