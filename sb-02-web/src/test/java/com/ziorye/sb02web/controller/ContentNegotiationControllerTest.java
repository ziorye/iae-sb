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
                        //.accept(MediaType.APPLICATION_JSON)
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

    @Test
    @DisplayName("测试 Content Negotiation 基于请求头的内容协商策略。客服端指定 Accept=application/xml 服务器会自动将原本的 json 格式 Person 转化为 xml 格式的 Person")
    void testHeaderContentNegotiationStrategyXml() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .accept(MediaType.APPLICATION_XML)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("/Person/userName").string("John"))
                .andExpect(MockMvcResultMatchers.xpath("/Person/age").string("18"))
        ;
    }
}
