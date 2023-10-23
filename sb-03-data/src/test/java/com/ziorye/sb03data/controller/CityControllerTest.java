package com.ziorye.sb03data.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    @Test
    @DisplayName("测试 Mybatis Mapper 配置文件")
    void testInsertCityUsingMybatis() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/city")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "xm")
                        .param("state", "fj")
                        .param("country", "CN")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("xm"))
                .andExpect(MockMvcResultMatchers.jsonPath("state").value("fj"))
                .andExpect(MockMvcResultMatchers.jsonPath("country").value("CN"))
        ;
    }
}