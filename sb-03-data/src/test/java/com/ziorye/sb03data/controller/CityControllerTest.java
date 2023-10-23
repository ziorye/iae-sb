package com.ziorye.sb03data.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql", "/data.sql"})
public class CityControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试 Mybatis 纯注解")
    void testFindByIdUsingMybatis() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/city/1")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("San Francisco"))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value("CA"))
                .andExpect(MockMvcResultMatchers.jsonPath("country").value("US"))
        ;
    }
}