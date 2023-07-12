package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.inspired.web.TodayController;

public class ApplicationContextConfigTest {

    Logger logger = LogManager.getLogger(ApplicationContextConfigTest.class);

    @Test
    void testConfig() {
        try {
            ApplicationContext context = new
                    ClassPathXmlApplicationContext("config.xml");


            TodayController controller = context.getBean("todayController", TodayController.class);
            Assertions.assertNotNull(controller);
        } catch (Exception e) {
            logger.error(e);
            Assertions.fail();
        }

    }
}
