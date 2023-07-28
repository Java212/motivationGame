package ru.inspired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.inspired.model.MotivationEvent;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<MotivationEvent> events() {
        return List.of(new MotivationEvent(1, "сделать что-то полезное", 5, 0),
                new MotivationEvent(2, "сделать что-то менее полезное", 3, 0)
        );
    }
}
