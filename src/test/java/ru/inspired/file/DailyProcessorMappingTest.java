package ru.inspired.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.MotivationEventDao;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.MotivationEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DailyProcessorMappingTest {

    DailyStatusFileDao processor = new DailyStatusFileDao(new MotivationEventDao() {
        @Override
        public List<MotivationEvent> getMotivationEvents() {
            return new LinkedList<>(); //как-то прочитали из фала список объектов MotivationEvent
        }

        @Override
        public MotivationEvent getEventById(int id) {
            return new MotivationEvent(id,"some task",1,0);
        }
    });
    @Test
    void testMapping() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        DailyStatus dailyStatus = processor.mapDailyLog(dateFormat,"06.06.23,10,DONE");
        Assertions.assertEquals(10, dailyStatus.getEvent().getId());
        Assertions.assertEquals("some task", dailyStatus.getEvent().getDescription());
        Assertions.assertEquals(CompletionState.DONE, dailyStatus.getStatus());
    }

    @Test
    void testWrongStateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> processor.mapDailyLog(dateFormat,"06.06.23,10,some_stupid_state"));

    }

    @Test
    void testWrongDateMapping() {
         SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                ()-> processor.mapDailyLog(dateFormat,"2424.24,10,DONE"));

    }

    @Test
    void testWrongLineMapping() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                ()-> processor.mapDailyLog(dateFormat,"not three parts separated by comma"));

    }
}
