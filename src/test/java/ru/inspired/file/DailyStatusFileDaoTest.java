package ru.inspired.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.inspired.MotivationEventDao;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.MotivationEvent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class DailyStatusFileDaoTest {

    @Mock
    MotivationEventDao dao;

    @InjectMocks
    DailyStatusFileDao processor;

    @Test
    void testMapping() throws ParseException {
        Mockito.when(dao.getEventById(10)).thenReturn(new MotivationEvent(10,"some task", 1,1));
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        DailyStatus dailyStatus = processor.mapDailyLog(dateFormat, "06.06.23,10,DONE");
        Assertions.assertEquals(10, dailyStatus.getEvent().getId());
        Assertions.assertEquals("some task", dailyStatus.getEvent().getDescription());
        Assertions.assertEquals(CompletionState.DONE, dailyStatus.getStatus());
    }

    @Test
    void testWrongStateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> processor.mapDailyLog(dateFormat, "06.06.23,10,some_stupid_state"));

    }

    @Test
    void testWrongDateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> processor.mapDailyLog(dateFormat, "2424.24,10,DONE"));

    }

    @Test
    void testWrongLineMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> processor.mapDailyLog(dateFormat, "not three parts separated by comma"));

    }

    @Test
    void testFileRead() throws IOException {
        Mockito.when(dao.getEventById(Mockito.anyInt())).thenReturn(new MotivationEvent(10,"some task", 1,1));
        List<DailyStatus> dailyStatus = processor.getDailyStatuses();
        Assertions.assertEquals(3, dailyStatus.size());
    }
}
