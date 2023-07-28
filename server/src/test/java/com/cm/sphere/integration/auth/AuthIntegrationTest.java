package com.cm.sphere.integration.auth;

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

@SpringBootTest
@AutoConfigureMockMvc
public class AuthIntegrationTest {
    private final MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(AuthIntegrationTest.class);

    @Autowired
    public AuthIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testAuthEndpointWithInvalidContentType() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                    .contentType(MediaType.TEXT_HTML)
                )
                .andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType())
                .andReturn();

        logger.trace(result.getResponse().getContentAsString());
    }
}
