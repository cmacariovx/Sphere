package com.cm.sphere.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.Arrays;
import java.util.List;

import com.cm.sphere.SphereApplication;
import com.cm.sphere.model.Request.SignupRequest;
import com.cm.sphere.model.Security.AuthUserDetails;
import com.cm.sphere.service.UserAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;

@SpringBootTest(classes = {SphereApplication.class})
public class AuthTest {
    private MockMvc mockMvc;
    private final JwtTokenUtilTest jwtTokenUtilTest;
    private final UserAuthService userAuthService;
    private final WebApplicationContext webApplicationContext;
    private static final String testUserId = "64c1a4aaefd1ab63c198f869";

    private static final Logger logger = LoggerFactory.getLogger(AuthTest.class);

    @Autowired
    public AuthTest(JwtTokenUtilTest jwtTokenUtilTest, UserAuthService userAuthService, WebApplicationContext webApplicationContext) {
        this.jwtTokenUtilTest = jwtTokenUtilTest;
        this.userAuthService = userAuthService;
        this.webApplicationContext = webApplicationContext;
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void testEndpointWithValidToken() throws Exception {
        final String token = jwtTokenUtilTest.generateValidTestToken(testUserId, 0);
        final Cookie refreshCookie = new Cookie("refresh_token", token);

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/fetchBasicData")
                .cookie(refreshCookie))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final MockHttpServletResponse mockResponse = result.getResponse();
        final List<Cookie> receivedCookies = Arrays.asList(mockResponse.getCookies());

        for (final Cookie cookie : receivedCookies) {
            if ("refresh_token".equals(cookie.getName())) {
                logger.trace("Found the refresh_token cookie!");
                break;
            }
        }

        logger.trace(result.getResponse().getContentAsString());
    }

    @Test
    public void testCookieReceival() throws Exception {
        final SignupRequest request = new SignupRequest("John", "Doe", "john.doe@example.com", "Password123#");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonRequest = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final MockHttpServletResponse mockResponse = result.getResponse();
        final List<Cookie> receivedCookies = Arrays.asList(mockResponse.getCookies());

        for (final Cookie cookie : receivedCookies) {
            if ("refresh_token".equals(cookie.getName())) {
                logger.trace("Found the refresh_token cookie!");
                break;
            }
        }

        logger.trace(result.getResponse().getContentAsString());
    }

    @Test
    public void testEndpointWithExpiredToken() throws Exception {
        final AuthUserDetails userDetails = userAuthService.loadUserByUsername(testUserId);
        final String token = jwtTokenUtilTest.generateExpiredTestToken(userDetails.getId(), 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/fetchBasicData")
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEndpointWithNoToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/fetchBasicData"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testEndpointWithTamperedToken() throws Exception {
        final String tamperedToken = "tamperedToken";

        mockMvc.perform(MockMvcRequestBuilders.get("/user/fetchBasicData")
                .header("Authorization", "Bearer " + tamperedToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
