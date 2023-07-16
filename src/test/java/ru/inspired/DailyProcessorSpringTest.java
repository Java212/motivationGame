package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class DailyProcessorSpringTest {

    @Spy
    MotivationEventDao dao = new MotivationEventTestDao();

    @InjectMocks
    DailyLogFileProcessor processor;

    @Test
    void testMapping() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        DailyLog dailyLog = processor.mapDailyLog(dateFormat, "06.06.23,10,DONE");
        Assertions.assertEquals(10, dailyLog.getEvent().getId());
        Assertions.assertEquals("some task", dailyLog.getEvent().getDescription());
        Assertions.assertEquals(CompletionStatus.DONE, dailyLog.getStatus());
    }

    @Test
    void testWrongStateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> processor.mapDailyLog(dateFormat, "06.06.23,10,some_stupid_state"));

    }

    @Test
    void testWrongDateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> processor.mapDailyLog(dateFormat, "2424.24,10,DONE"));

    }

    @Test
    void testWrongLineMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyLogFileProcessor.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> processor.mapDailyLog(dateFormat, "not three parts separated by comma"));

    }

    @Test
    void testFileRead() throws IOException {
        List<DailyLog> dailyLog = processor.getLog();
        Assertions.assertEquals(3, dailyLog.size());
    }
}
