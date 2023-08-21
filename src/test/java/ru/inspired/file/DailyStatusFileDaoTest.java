package ru.inspired.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class DailyStatusFileDaoTest {

    DailyStatusFileDao dao = new DailyStatusFileDao();

    @Test
    void testMapping() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        DailyStatus dailyStatus = dao.mapDailyLog(dateFormat, "06.06.23,10,DONE,5");
        Assertions.assertEquals(10, dailyStatus.getMotivationEventId());
        Assertions.assertEquals(CompletionState.DONE, dailyStatus.getState());
        Assertions.assertEquals(5, dailyStatus.getCalculationScore());
    }

    @Test
    void testWrongStateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dao.mapDailyLog(dateFormat, "06.06.23,10,some_stupid_state,5"));

    }

    @Test
    void testWrongDateMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> dao.mapDailyLog(dateFormat, "2424.24,10,DONE,5"));

    }

    @Test
    void testWrongLineMapping() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DailyStatusFileDao.DATE_FORMAT);
        Assertions.assertThrows(ParseException.class,
                () -> dao.mapDailyLog(dateFormat, "not three parts separated by comma"));

    }

    @Test
    void testFileRead()  {
        List<DailyStatus> dailyStatus = dao.getDailyStatuses(1);
        Assertions.assertEquals(3, dailyStatus.size());
    }
}
