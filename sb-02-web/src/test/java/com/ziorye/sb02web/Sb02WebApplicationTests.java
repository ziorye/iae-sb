package com.ziorye.sb02web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Sb02WebApplicationTests {

	@Test
	void screenshot(@Autowired MockMvc mvc) throws Exception {
		for (String img : Arrays.asList(
				"New Module using Spring Initializr - 1.png",
				"New Module using Spring Initializr - 2 - select dependencies.png")
		) {
			mvc.perform(get("/" + img))
					.andExpect(status().isOk())
					.andExpect(content().contentType("image/png"));
		}
	}

}
