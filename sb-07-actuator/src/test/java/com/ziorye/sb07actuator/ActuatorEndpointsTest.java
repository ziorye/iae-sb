package com.ziorye.sb07actuator;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

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

    @Test
    @DisplayName("测试配置了 `management.endpoint.health.show-details=always` 之后的 /actuator/health 页面")
    void actuatorHealthShowDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("components.diskSpace.status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("components.ping.status").value("UP"))
        ;
    }

    @Test
    @DisplayName("测试增加了自定义 CustomHealthIndicator 之后的 /actuator/health 页面内容")
    void actuatorCustomHealthIndicator() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("status", Matchers.in(Arrays.asList("UP", "DOWN"))))
                .andExpect(MockMvcResultMatchers.jsonPath("components.custom.status", Matchers.in(Arrays.asList("UP", "DOWN"))))
                .andExpect(MockMvcResultMatchers.jsonPath("components.custom.details.message", Matchers.in(Arrays.asList("is even", "is odd"))))
                .andExpect(MockMvcResultMatchers.jsonPath("components.diskSpace.status").value("UP"))
                .andExpect(MockMvcResultMatchers.jsonPath("components.ping.status").value("UP"))
        ;
    }

    @Test
    @DisplayName("测试 /actuator/health 页面内容的细节：但凡有一个 components 的 status 等于 DOWN，最外层的 status 就等于 DOWN")
    void actuatorCustomHealthIndicatorDetail() throws Exception {
        String response = mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andReturn().getResponse().getContentAsString();

        String appStatus = JsonPath.read(response, "status");
        String componentStatus = JsonPath.read(response, "components.custom.status");
        // 假设：其他 component 的 status 都等于 UP
        // 因此，这个测试的逻辑是：最外层 status 的值 == 自定义 component 的 status 值

        Assertions.assertEquals(appStatus, componentStatus);
    }
}
