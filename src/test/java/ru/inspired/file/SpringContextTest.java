package ru.inspired.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "file")
public class SpringContextTest {

    @Autowired
    private TestRestTemplate template;


    @Test
    public void getLoginPage() {

        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).contains("<title>Добро пожаловать в игру</title>");
    }

}
