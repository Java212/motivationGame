package ru.inspired.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import ru.inspired.DailyStatusDao;
import ru.inspired.MotivationEventDao;
import ru.inspired.model.MotivationEvent;
import ru.inspired.notes.NotesDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodayViewTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    MotivationEventDao eventDao;

    @MockBean
    DailyStatusDao statusDao;

    @MockBean
    NotesDao notesDao;

    @Test
    public void getMotivationEvents() {
        Mockito.when(eventDao.getMotivationEvents())
                .thenReturn(List.of(new MotivationEvent(1, "text", 1, 1)));
        ResponseEntity<String> response = template.getForEntity("/today", String.class);
        assertThat(response.getBody()).contains("form method=\"post\"");
    }

}
