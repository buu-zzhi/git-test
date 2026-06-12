package com.example.gittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testEndpointReturnsGreeting() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Springboot"));

        mockMvc.perform(get("/test2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello mybatis"));

        mockMvc.perform(get("/test3"))
                .andExpect(status().isOk())
                .andExpect(content().string("HPL"));
    }
}
