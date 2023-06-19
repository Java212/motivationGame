package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;
import ru.inspired.model.MotivationEvent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DailyProcessorMappingTest {

    @Test
    void testMapping() throws ParseException {
        MotivationEventDao eventDao = new MotivationEventFileDao();
        DailyLogFileProcessor processor = new DailyLogFileProcessor(eventDao);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        DailyLog dailyLog = processor.mapDailyLog(dateFormat,"06.06.23,10,DONE");
        Assertions.assertEquals(10, dailyLog.getEvent().getId());
        Assertions.assertEquals("some task", dailyLog.getEvent().getDescription());
        Assertions.assertEquals(CompletionStatus.DONE, dailyLog.getStatus());
    }

    @Test
    void testWrongStateMapping() {
        MotivationEventDao eventDao = new MotivationEventFileDao();
        DailyLogFileProcessor processor = new DailyLogFileProcessor(eventDao);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> processor.mapDailyLog(dateFormat,"06.06.23,10,some_stupid_state"));

    }

    @Test
    void testWrongDateMapping() {
        MotivationEventDao eventDao = new MotivationEventFileDao();
        DailyLogFileProcessor processor = new DailyLogFileProcessor(eventDao);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                ()-> processor.mapDailyLog(dateFormat,"2424.24,10,DONE"));

    }

    @Test
    void testWrongLineMapping() {
        MotivationEventDao eventDao = new MotivationEventFileDao();
        DailyLogFileProcessor processor = new DailyLogFileProcessor(eventDao);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                ()-> processor.mapDailyLog(dateFormat,"not three parts separated by comma"));

    }

    @Test
    void testFileRead() throws IOException {
        MotivationEventDao eventDao = new MotivationEventFileDao();
        DailyLogFileProcessor processor = new DailyLogFileProcessor(eventDao);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        List<DailyLog> dailyLog = processor.getLog();
        Assertions.assertEquals(3,dailyLog.size());
    }
}
