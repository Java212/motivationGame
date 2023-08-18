package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DailyLogFileProcessorTest {
    @Test
    void test_getLog() throws IOException {
        MotivationEventDao eventDao = new MotivationEventTestDao();
        DailyLogFileProcessor logProcessor = new DailyLogFileProcessor(eventDao);
        List<DailyLog> list = logProcessor.getLog();
        Assertions.assertEquals(3,list.size());
        System.out.println();
        CompletionStatus status = list.get(0).getStatus();
        Assertions.assertEquals(CompletionStatus.DONE, status);
    }

}
