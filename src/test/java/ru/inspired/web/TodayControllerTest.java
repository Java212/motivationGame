package ru.inspired.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("db")
class TodayControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                //.apply(springSecurity()) // enable security for the mock set up
                .build();
    }

    @Test
    @WithMockUser
    void testGet() throws Exception {
        String url = "/today";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testPostWithOneEvent() throws Exception {
        String url = "/today";

        this.mockMvc.perform(post(url).param("boxes", "1")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attribute("yesterday", 0))
                .andExpect(model().attribute("now", 5));

    }

    @Test
    @WithMockUser
    void testPostWithNoEvents() throws Exception {
        String url = "/today";

        this.mockMvc.perform(post(url).with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk());
    }
}