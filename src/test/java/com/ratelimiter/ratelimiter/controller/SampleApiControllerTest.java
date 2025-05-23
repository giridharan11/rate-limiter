package com.ratelimiter.ratelimiter.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleApiController.class)
class SampleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDefaultRateLimit() throws Exception {
        for (int i = 0; i < 3; i++) {
            mockMvc.perform(get("/api/default")
                            .header("X-User-Id", "user1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }
}