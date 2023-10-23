package com.ziorye.sb02web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ThymeleafQuickstartControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void quickstart() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/thymeleaf/quickstart"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}