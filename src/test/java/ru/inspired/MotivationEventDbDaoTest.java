package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.inspired.model.MotivationEvent;
import ru.inspired.model.User;

import java.util.List;

@SpringBootTest
@ActiveProfiles("db")
public class MotivationEventDbDaoTest {

    @Autowired
    MotivationEventDao dao;

    @Test
    void testReading() {

        User user = new User(1);
        List<MotivationEvent> qs = dao.getMotivationEvents(user);
        Assertions.assertEquals(2, qs.size());
        Assertions.assertEquals("Почистить зубы 2 раза", qs.get(0).getDescription());
    }

}
