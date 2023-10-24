package com.ioewvsau;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasItem;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql", "/data.sql"})
public class SwaggerUITest {
    @Autowired
    MockMvc mvc;

    @Test
    void swaggerUI() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/swagger-ui.html"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/swagger-ui/index.html"))
        ;
    }

    @Test
    void swaggerUIActual() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/swagger-ui/index.html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("swagger-ui")))
        ;
    }

    @Test
    @DisplayName("""
            @Tag(name = "post-controller", description = "文章管理")
            """)
    void swaggerUIWithTagAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("tags.*.name", hasItem("post-controller")))
                .andExpect(MockMvcResultMatchers.jsonPath("tags.*.description", hasItem("文章管理")))
        ;
    }
}
