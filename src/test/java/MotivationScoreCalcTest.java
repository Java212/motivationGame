
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.MotivationScoreCalc;
import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;
import ru.inspired.model.MotivationEvent;

import java.util.List;

public class MotivationScoreCalcTest {

    Logger logger = LogManager.getLogger(MotivationScoreCalcTest.class);

    @Test
    void testCalculationOfSingle() {
        MotivationEvent event = new MotivationEvent(1,"test",5,0);
        List<DailyLog> list = List.of(new DailyLog(event, CompletionStatus.DONE));
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        logger.warn("test is ending");
        Assertions.assertEquals(5,calc.calculateScore(list));
    }

    @Test
    void testCalculationOfTwo() {
        MotivationEvent event = new MotivationEvent(1,"test",5,5);
        List<DailyLog> list = List.of(new DailyLog(event, CompletionStatus.DONE), new DailyLog(event, CompletionStatus.FAILED));
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        Assertions.assertEquals(0,calc.calculateScore(list));
    }
}
