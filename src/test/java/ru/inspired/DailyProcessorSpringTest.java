package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class DailyProcessorSpringTest {

    static DailyLogFileProcessor processorUnderTest;

    @BeforeAll
    static void beforeAll() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("test-config-short.xml"));
        processorUnderTest = factory.getBean("dailyLogProcessor", DailyLogFileProcessor.class);
    }

    @Test
    void testMapping() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        DailyLog dailyLog = processorUnderTest.mapDailyLog(dateFormat,"06.06.23,10,DONE");
        Assertions.assertEquals(10, dailyLog.getEvent().getId());
        Assertions.assertEquals("some task", dailyLog.getEvent().getDescription());
        Assertions.assertEquals(CompletionStatus.DONE, dailyLog.getStatus());
    }

    @Test
    void testWrongStateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> processorUnderTest.mapDailyLog(dateFormat,"06.06.23,10,some_stupid_state"));

    }

    @Test
    void testWrongDateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                ()-> processorUnderTest.mapDailyLog(dateFormat,"2424.24,10,DONE"));

    }

    @Test
    void testWrongLineMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                ()-> processorUnderTest.mapDailyLog(dateFormat,"not three parts separated by comma"));

    }

    @Test
    void testFileRead() throws IOException {
        List<DailyLog> dailyLog = processorUnderTest.getLog();
        Assertions.assertEquals(3,dailyLog.size());
    }

    @Test
    void testFileReadAndContent() throws IOException {
        List<DailyLog> log = processorUnderTest.getLog();

        Assertions.assertEquals(3, log.size());

        DailyLog log1 = log.get(0);
        Assertions.assertEquals(LocalDate.of(2023, 6, 5), log1.getDate());
        Assertions.assertEquals(1, log1.getEvent().getId());
        Assertions.assertEquals(CompletionStatus.DONE, log1.getStatus());

        DailyLog log2 = log.get(1);
        Assertions.assertEquals(LocalDate.of(2023, 6, 10), log2.getDate());
        Assertions.assertEquals(2, log2.getEvent().getId());
        Assertions.assertEquals(CompletionStatus.FAILED, log2.getStatus());

        DailyLog log3 = log.get(2);
        Assertions.assertEquals(LocalDate.of(2023, 6, 12), log3.getDate());
        Assertions.assertEquals(3, log3.getEvent().getId());
        Assertions.assertEquals(CompletionStatus.FAILED, log3.getStatus());
    }

}
