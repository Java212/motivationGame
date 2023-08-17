package ru.inspired.notes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "db")
public class NotesViewTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getNotes() {
        ResponseEntity<String> response = restTemplate.getForEntity("/notes", String.class);
        assertThat(response.getBody()).contains("<h2>Список заметок:</h2>");
    }
}