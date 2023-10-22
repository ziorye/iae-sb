package com.ziorye.sb02web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContentNegotiationControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试 Content Negotiation 基于请求头的内容协商策略。@ResponseBody 默认会返回 json 格式的 Person")
    void testHeaderContentNegotiationStrategyJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                )
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("userName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value(18))
        ;
    }

    @Test
    @DisplayName("测试跟踪 Content Negotiation and @RequestBody")
    void testVersionAgnosticURLsForWebjars() throws Exception {
        mvc.perform(get("/atrequestbody.html")).andExpect(status().isOk());
    }
}
