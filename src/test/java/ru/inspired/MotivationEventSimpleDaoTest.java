package ru.inspired;



import java.util.ArrayList;

import java.util.NoSuchElementException;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.MotivationEvent;

public class MotivationEventSimpleDaoTest {


    @Test
    void testConstructor() {
        ArrayList<MotivationEvent> motivationEventList = new ArrayList<>();
        Assertions.assertEquals(motivationEventList, (new MotivationEventSimpleDao(motivationEventList)).getMotivationEvents());
    }




    @Test
    void testGetEventById() throws NoSuchElementException {
        ArrayList<MotivationEvent> motivationEventList = new ArrayList<>();
        motivationEventList.add(new MotivationEvent(2, "Почищены зубы 2 раза", 5, 5));
        MotivationEvent motivationEvent = new MotivationEvent(1, "Сделана утренняя зарядка", 5, 5);

        motivationEventList.add(motivationEvent);
        Assertions.assertEquals(motivationEvent, (new MotivationEventSimpleDao(motivationEventList)).getEventById(1));
    }


}