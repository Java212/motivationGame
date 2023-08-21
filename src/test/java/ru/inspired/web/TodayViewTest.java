package ru.inspired.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.inspired.DailyStatusDao;
import ru.inspired.MotivationEventDao;
import ru.inspired.model.MotivationEvent;
import ru.inspired.model.User;
import ru.inspired.notes.NotesDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TodayViewTest {

    @MockBean
    MotivationEventDao eventDao;

    @MockBean
    DailyStatusDao statusDao;

    @MockBean
    NotesDao notesDao;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // enable security for the mock set up
                .build();
    }

    @Test
    @WithMockUser(value = "admin", password = "admin")
    public void getMotivationEvents() throws Exception {
        Mockito.when(eventDao.getMotivationEvents(Mockito.any(User.class)))
                .thenReturn(List.of(new MotivationEvent(1, "text", 1, 1)));
        String response = mvc.perform(get("/today"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(response.contains("<label>text<label>"));
        assertThat(response.contains("<input type=\"hidden\" name=\"_csrf\""));
    }

    @Test
    public void getMotivationEventsNoUser() throws Exception {
        Mockito.when(eventDao.getMotivationEvents(Mockito.any(User.class)))
                .thenReturn(List.of(new MotivationEvent(1, "text", 1, 1)));
        mvc.perform(get("/today"))
                .andExpect(status().is3xxRedirection());

    }

}
