package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.MotivationEvent;

import java.util.List;

public class MotivationScoreCalcTest {

    Logger logger = LogManager.getLogger(MotivationScoreCalcTest.class);

    @Test
    void testCalculationOfSingle() {
        MotivationEvent event = new MotivationEvent(1,"test",5,0);
        List<DailyStatus> list = List.of(new DailyStatus(event, CompletionState.DONE));
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        logger.warn("test is ending");
        Assertions.assertEquals(5,calc.calculateScore(list));
    }

    @Test
    void testCalculationOfTwo() {
        MotivationEvent event = new MotivationEvent(1,"test",5,5);
        List<DailyStatus> list = List.of(new DailyStatus(event, CompletionState.DONE), new DailyStatus(event, CompletionState.FAILED));
        MotivationScoreCalc calc = new MotivationScoreCalc(0);
        Assertions.assertEquals(0,calc.calculateScore(list));
    }
}
