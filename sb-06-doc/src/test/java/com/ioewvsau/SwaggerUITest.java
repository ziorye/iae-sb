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

import static org.hamcrest.Matchers.*;

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

    @Test
    @DisplayName("""
            @Operation(summary = "文章列表", description = "支持分页的文章列表接口，默认显示第一页(page=1), 每页显示10条(perPage=10)")
            """)
    void swaggerUIWithOperationAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("paths['/posts'].get.summary", is("文章列表")))
                .andExpect(MockMvcResultMatchers.jsonPath("paths['/posts'].get.description", is("支持分页的文章列表接口，默认显示第一页(page=1), 每页显示10条(perPage=10)")))
        ;
    }

    @Test
    @DisplayName("""
            @Parameter(description = "当前页码")
            @Parameter(description = "每页显示数量")
            """)
    void swaggerUIWithParameterAnnotation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/v3/api-docs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("paths['/posts'].get.parameters.*.description", hasItems("当前页码", "每页显示数量")))
        ;
    }
}
