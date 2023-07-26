package com.cm.sphere.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.cm.sphere.model.Security.AuthUserDetails;
import com.cm.sphere.service.UserAuthService;

@SpringBootTest
public class WebSecurityConfigTest {
    private MockMvc mockMvc;
    private final JwtTokenUtilTest jwtTokenUtilTest;
    private final UserAuthService userAuthService;
    private final WebApplicationContext webApplicationContext;
    private static final String testUserId = "64c12c8a2432ee0f976b858d";

    @Autowired
    public WebSecurityConfigTest(JwtTokenUtilTest jwtTokenUtilTest, UserAuthService userAuthService, WebApplicationContext webApplicationContext) {
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
        // final AuthUserDetails userDetails = userAuthService.loadUserByUsername(testUserId);
        final String token = jwtTokenUtilTest.generateValidTestToken(testUserId, 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/fetchBasicData")
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEndpointWithExpiredToken() throws Exception {
        final AuthUserDetails userDetails = userAuthService.loadUserByUsername(testUserId);
        final String token = jwtTokenUtilTest.generateExpiredTestToken(userDetails.getId(), 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/fetchBasicData")
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEndpointWithNoToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/fetchBasicData"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testEndpointWithTamperedToken() throws Exception {
        final String tamperedToken = "tamperedToken";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/fetchBasicData")
                .header("Authorization", "Bearer " + tamperedToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
