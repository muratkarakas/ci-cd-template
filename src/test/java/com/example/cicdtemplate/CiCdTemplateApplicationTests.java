package com.example.cicdtemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CiCdTemplateApplicationTests {

    //TODO test this
	@Autowired
    private MockMvc mockMvc;
	@Test
	@DisplayName("When Pinged Then Should Return Status 200 and Pong")
	void ping() throws Exception {
		mockMvc.perform(get("/ping")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("pong"));
	}

}
