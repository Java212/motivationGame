package ru.inspired.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DailyProcessorMappingTest {

    DailyStatusFileDao processor = new DailyStatusFileDao();

    @Test
    void testMapping() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        DailyStatus dailyStatus = processor.mapDailyLog(dateFormat, "06.06.23,10,DONE,5");
        Assertions.assertEquals(10, dailyStatus.getMotivationEventId());
        Assertions.assertEquals(CompletionState.DONE, dailyStatus.getState());
    }

    @Test
    void testWrongStateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> processor.mapDailyLog(dateFormat, "06.06.23,10,some_stupid_state,5"));

    }

    @Test
    void testWrongDateMapping() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> processor.mapDailyLog(dateFormat, "2424.24,10,DONE,5"));

    }

    @Test
    void testWrongLineMapping() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> processor.mapDailyLog(dateFormat, "not three parts separated by comma"));

    }
}
