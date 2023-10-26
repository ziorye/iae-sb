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

@SpringBootTest(properties = "management.endpoints.web.exposure.include=*")
@AutoConfigureMockMvc
public class ActuatorEndpointsExposureAllTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试配置了 `management.endpoints.web.exposure.include=*` 之后的 /actuator 页面内容")
    void actuatorExposureAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator"))
                .andExpect(MockMvcResultMatchers.jsonPath("_links.*.href", Matchers.hasSize(Matchers.greaterThan(19))))
        ;
    }

    @Test
    @DisplayName("测试 /actuator/beans 页面内容")
    void actuatorBeans() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/beans"))
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.beans.dispatcherServlet").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.beans.defaultServletHandlerMapping").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.beans.requestMappingHandlerAdapter").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.beans.viewResolver").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.beans['org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration']").exists())
        ;
    }

    @Test
    @DisplayName("测试 /actuator/conditions 页面内容")
    void actuatorConditions() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/conditions"))
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.positiveMatches.DispatcherServletAutoConfiguration").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.positiveMatches['ServletWebServerFactoryConfiguration.EmbeddedTomcat']").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.negativeMatches['ServletWebServerFactoryConfiguration.EmbeddedUndertow']").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.negativeMatches['ServletWebServerFactoryConfiguration.EmbeddedJetty']").exists())
        ;
    }

    @Test
    @DisplayName("测试 /actuator/mappings 页面内容")
    void actuatorMappings() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/mappings"))
                .andExpect(MockMvcResultMatchers.jsonPath("contexts.application.mappings.dispatcherServlets").exists())
        ;
    }

    @Test
    @DisplayName("测试 /actuator/metrics 页面内容")
    void actuatorMetrics() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/metrics"))
                .andExpect(MockMvcResultMatchers.jsonPath("names", Matchers.hasItems("application.started.time", "disk.total", "system.cpu.count", "system.cpu.usage")))
        ;
    }

    @Test
    @DisplayName("测试 /actuator/metrics/application.started.time 页面内容")
    void actuatorMetricsDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/actuator/metrics/application.started.time"))
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is("application.started.time")))
        ;
    }
}
