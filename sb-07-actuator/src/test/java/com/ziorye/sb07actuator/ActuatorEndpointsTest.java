package com.ziorye.sb07actuator;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorEndpointsTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试引入 actuator starter 之后默认增加的 actuator 入口页面")
    void actuator() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator"))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.self.href", Matchers.endsWith("/actuator")))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.health.href", Matchers.endsWith("/actuator/health")))
        ;
    }

    @Test
    @DisplayName("测试 /actuator/health 页面")
    void actuatorHealth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("UP"))
        ;
    }
}
