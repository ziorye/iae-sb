package com.ziorye.sb03data.controller;

import org.hamcrest.Matchers;
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

import static org.springframework.web.servlet.function.ServerResponse.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql", "/data.sql"})
public class UserControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试继承自 MyBatis-Plus 的 service")
    void testFindByIdUsingMybatis() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/users")
                )
                .andExpect(MockMvcResultMatchers.view().name("users"))
        ;
    }
}