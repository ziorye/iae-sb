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

    @Test
    @DisplayName("测试 Content Negotiation 基于请求参数的内容协商策略。访问地址附带 format=json 请求参数")
    void testParameterContentNegotiationStrategyWithFormatJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .queryParam("format", "json")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    @DisplayName("测试 Content Negotiation 基于请求参数的内容协商策略。访问地址附带 format=xml 请求参数")
    void testParameterContentNegotiationStrategyWithFormatXml() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .queryParam("format", "xml")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
        ;
    }

    @Test
    @DisplayName("测试 Content Negotiation 添加自定义 HttpMessageConverter")
    void testCustomMessageConverter() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .header("Accept", "application/x-custom")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("John;18;other attributes are omitted"))
        ;
    }
}
