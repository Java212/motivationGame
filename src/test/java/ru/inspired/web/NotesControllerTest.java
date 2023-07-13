package ru.inspired.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotesControllerTest {


    @Autowired
    MockMvc mockMvc;

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