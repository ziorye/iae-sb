package com.ioewvsau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioewvsau.pojo.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql", "/data.sql"})
class PostControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("*.slug", hasSize(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("*.slug", hasItems("maven-repository-speedup", "just-for-wireframes")))
        ;
    }

    @Test
    void indexWithPage() throws Exception {
        int perPage = 3;
        mvc.perform(MockMvcRequestBuilders.get("/posts").queryParam("page", "2").queryParam("perPage", perPage+""))
                .andExpect(MockMvcResultMatchers.jsonPath("*.slug", hasSize(perPage)))
        ;
    }

    @Test
    void store() throws Exception {
        Post post = new Post();
        post.setTitle("post-title-11");
        post.setSlug("post-title-11");
        post.setCover("/img/cover/default-cover.png");
        post.setDescription("post-title-11 description");
        post.setContent("## post-title-11 content\\nthe content paragraph 1...");
        post.setStatus(1);
        post.setUserId(1);

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("1"))
        ;
    }

    @Test
    void show() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/posts/5"))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value("||||||||||||||||||||||||||||||"))
                .andExpect(MockMvcResultMatchers.jsonPath("slug").value("just-for-wireframes"))
        ;
    }

    @Test
    void update() throws Exception {
        Post post = new Post();
        post.setTitle("just-for-wireframes__update");
        post.setStatus(0);

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(put("/posts/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(is("1")))
        ;
    }

    @Test
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/posts/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("1"))
        ;
    }
}