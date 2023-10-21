package com.ziorye.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Test
    void home(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    @DisplayName("测试 yaml 配置文件与配置类的映射")
    void testYaml(@Autowired MockMvc mvc) throws Exception {
        String expected = "{\"description\":\"YAML Ain't Markup Language™\",\"whatItIs\":\"YAML is a human-friendly data serialization language for all programming languages.\",\"easyToUse\":true,\"version\":1.2,\"releaseDate\":\"2009-07-20T16:00:00.000+00:00\",\"authors\":[\"Oren Ben-Kiki\",\"Clark Evans\",\"Ingy döt Net\"],\"goals\":[\"YAML is easily readable by humans.\",\"YAML matches the native data structures of agile languages.\",\"YAML data is portable between programming languages.\",\"YAML has a consistent model to support generic tools.\",\"YAML supports one-pass processing.\",\"YAML is expressive and extensible.\",\"YAML is easy to implement and use.\"],\"frameworksAndTools\":{\"Golang\":{\"0\":\"Go-yaml\",\"1\":\"Go-gypsy\",\"2\":\"libcyaml\",\"3\":\"goccy/go-yaml\"},\"Java\":{\"0\":\"SnakeYAML Engine\",\"1\":\"SnakeYAML\",\"2\":\"YamlBeans\",\"3\":\"eo-yaml\",\"4\":\"Chronicle-Wire\"},\"JavaScript\":{\"yaml\":\"JavaScript parser/stringifier (YAML 1.2, 1.1)\",\"js-yaml\":\"Native PyYAML port to JavaScript\"}},\"revisions\":[\"1.0\",\"1.1\",\"1.2\"],\"specification\":{\"name\":\"YAML 1.2\"},\"specifications\":{\"current\":[{\"name\":\"YAML 1.2\"}],\"others\":[{\"name\":\"YAML 1.1\"},{\"name\":\"YAML 1.0\"}]}}";
        mvc.perform(get("/yaml"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("description").value("YAML Ain't Markup Language™"))
                .andExpect(MockMvcResultMatchers.jsonPath("whatItIs").value("YAML is a human-friendly data serialization language for all programming languages."))
                .andExpect(MockMvcResultMatchers.jsonPath("specification.name").value("YAML 1.2"))
                .andExpect(MockMvcResultMatchers.jsonPath("specification.url").value("https://yaml.org/spec/1.2/"))
                .andExpect(content().json(expected));
    }
}