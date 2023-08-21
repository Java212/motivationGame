package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("db")
public class DailyStatusDbDaoTest {

    @Autowired
    DailyStatusDao dao;

    @Test
    void testReading() {
        Assertions.assertDoesNotThrow(() -> dao.getDailyStatuses(1));

    }

}
