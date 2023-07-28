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
class TodayControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGet() throws Exception {
        String url = "/today";

        this.mockMvc.perform(get(url))
                .andExpect(model().size(1))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWithOneEvent() throws Exception {
        String url = "/today";

        this.mockMvc.perform(post(url).content("1=on"))
                .andExpect(model().size(2))
                .andExpect(model().attribute("yesterday", 5))
                .andExpect(model().attribute("now", 10))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWithNoEvents() throws Exception {
        String url = "/today";

        this.mockMvc.perform(post(url).content(""))
                .andExpect(status().isOk());
    }
}