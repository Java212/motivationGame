package ru.inspired.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodayViewTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getMotivationEvents()  {
        ResponseEntity<String> response = template.getForEntity("/today", String.class);
        assertThat(response.getBody()).contains("form method=\"post\"");
    }

}
