package ru.inspired.notes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "db")
public class NotesViewTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    JdbcTemplate template;

    @BeforeEach
    public void insertData() {
        template.execute("DROP TABLE IF EXISTS notes");
        template.execute("""
                        CREATE TABLE notes
                        (
                            id IDENTITY NOT NULL ,
                            text varchar NOT NULL,
                            date_time timestamp default CURRENT_TIMESTAMP(),
                            CONSTRAINT notes_pkey PRIMARY KEY (id)
                        );
                """);
        template.execute("insert into notes(id,text) values(1, 'test message')");
    }


    @Test
    public void getNotes() {
        ResponseEntity<String> response = restTemplate.getForEntity("/notes", String.class);
        assertThat(response.getBody()).contains("<h2>Список заметок:</h2>");
    }
}