package ru.inspired.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(locations = "classpath:config.xml")
class NotesControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testGet() throws Exception {
        String url = "/notes";

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(status().isOk());
    }

    @Test
    void testPost() throws Exception {
        String url = "/notes";

        this.mockMvc.perform(post(url).content("hello,wold"))
                .andExpect(model().size(1))
                .andExpect(status().isOk());
    }
}