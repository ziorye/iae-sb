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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class MethodParameterControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("测试 @PathVariable 注解")
    void testPathVariable() throws Exception {
        String personId = "8";
        String petName = "tomcat";

        String expectedContent = "{\"personId\":\"" + personId + "\", \"petName\":\"" + petName + "\", \"varMap\":{\"personId\":\"" + personId + "\",\"petName\":\"" + petName + "\"}}";

        mvc.perform(MockMvcRequestBuilders.get("/person/{personId}/pet/{petName}", personId, petName))
                .andExpect(content().json(expectedContent));
    }

    @Test
    @DisplayName("测试 @RequestHeader 注解")
    void testRequestHeader() throws Exception {
        String host = "localhost:8080";

        String connection = "keep-alive";
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36";
        Map<String, String> map = new HashMap<>();
        map.put("host", host);
        map.put("connection", connection);
        map.put("user-agent", userAgent);


        mvc.perform(MockMvcRequestBuilders
                .get("/requestHeader")
                .header("host", host)
                .header("connection", connection)
                .header("user-agent", userAgent)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("host").value(host))
                .andExpect(MockMvcResultMatchers.jsonPath("headers").value(map))
        ;
    }

    @Test
    @DisplayName("测试 @RequestParam 注解")
    void testRequestParam() throws Exception {
        String name = "john";
        String pet = "cat";
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("pet", pet);

        mvc.perform(MockMvcRequestBuilders
                .get("/requestParam")
                .param("name", name)
                .param("pet", pet)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("pet").value(pet))
                .andExpect(MockMvcResultMatchers.jsonPath("map").value(map))
        ;
    }

    @Test
    @DisplayName("测试 @RequestBody 注解")
    void testRequestBody() throws Exception {
        String expectedContent = "{\"name\":\"john\", \"age\":18}";

        mvc.perform(MockMvcRequestBuilders
                .post("/requestBody")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedContent)
        )
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("测试 @RequestAttribute 注解")
    void testRequestAttribute() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/go"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/success"))
                .andExpect(MockMvcResultMatchers.request().attribute("msg", "from forwardToSuccessPage"))
                .andExpect(MockMvcResultMatchers.request().attribute("code", 188))
        ;

        // 进一步测试返回结果是否正确
    }

    @Test
    @DisplayName("测试 Map 和 Model 类型的方法参数")
    void testMapAndModel() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/mapAndModel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/checkAttribute"))
                .andExpect(MockMvcResultMatchers.request().attribute("mapKey", "mapValue"))
                .andExpect(MockMvcResultMatchers.request().attribute("modelAttributeName", "modelAttributeValue"))
                .andExpect(MockMvcResultMatchers.request().attribute("requestAttributeName", "requestAttributeValue"))
        ;
    }

    @Test
    @DisplayName("测试将自己写的 Person 类型作为方法参数。Spring Boot 会根据请求内容自动封装成 Person 对象")
    void testPersonMethodParameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/savePerson")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "John")
                .param("age", "18")
                .param("birth", "1999/09/09")
                .param("pet.name", "tomcat")
                .param("pet.weight", "5.5")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("userName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value(18))
                .andExpect(MockMvcResultMatchers.jsonPath("birth").value("1999-09-09T00:00:00.000+08:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("pet.name").value("tomcat"))
                .andExpect(MockMvcResultMatchers.jsonPath("pet.weight").value("5.5"))
        ;
    }

    @Test
    @DisplayName("测试 StringToPetConverter 自定义 Converter。将 String 转 Pet")
    void testCustomConverter() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/savePerson")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "John")
                        .param("age", "18")
                        .param("birth", "1999/09/09")
                        .param("pet", "tomcat,5.8")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("userName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value(18))
                .andExpect(MockMvcResultMatchers.jsonPath("birth").value("1999-09-09T00:00:00.000+08:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("pet.name").value("tomcat"))
                .andExpect(MockMvcResultMatchers.jsonPath("pet.weight").value("5.8"))
        ;
    }
}
