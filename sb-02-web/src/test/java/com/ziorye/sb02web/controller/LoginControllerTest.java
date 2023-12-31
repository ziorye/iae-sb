package com.ziorye.sb02web.controller;

import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void show() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(content().string(StringContains.containsString("/webjars/")))
        ;
    }

    @Test
    @DisplayName("测试登录成功")
    void testLoginSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test@test.com")
                        .param("password", "secret")
                )
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("loginUser", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"))
                .andExpect(MockMvcResultMatchers.flash().attribute("msg", "got it!"))
        ;
    }

    @Test
    @DisplayName("测试登录失败")
    void testLoginFail() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test@test.com")
                        .param("password", "incorrect-password")
                )
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("loginUser", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"))
                .andExpect(MockMvcResultMatchers.flash().attribute("msg", "邮箱或密码错误"))
        ;
    }

    @Test
    @DisplayName("测试未登录访问首页")
    void testVisitHomePageWithoutLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/home")
                )
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("loginUser", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.model().attribute("msg", "未登录"))
        ;
    }

    @Test
    @DisplayName("测试国际化 - zh - 模拟浏览器切换语言为中文")
    void i18nCN() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/login")
                        .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                )
                .andExpect(content().string(StringContains.containsString("邮箱")))
        ;
    }

    @Test
    @DisplayName("测试国际化 - en - 模拟浏览器切换语言为英文")
    void i18nUS() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/login")
                        .header("Accept-Language", "en,zh-CN;q=0.9,zh;q=0.8")
                )
                .andExpect(content().string(StringContains.containsString("Email address")))
        ;
    }
}