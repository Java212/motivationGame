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
class TodayControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

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
                .andExpect(model().attribute("yesterday", 0))
                .andExpect(model().attribute("now", 5))
                .andExpect(status().isOk());
    }

    @Test
    void testPostWithNoEvents() throws Exception {
        String url = "/today";

        this.mockMvc.perform(post(url).content(""))
                    .andExpect(status().isOk());
    }
}