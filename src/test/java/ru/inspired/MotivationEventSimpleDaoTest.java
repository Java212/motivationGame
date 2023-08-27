package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.inspired.model.MotivationEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class MotivationEventSimpleDaoTest {
    @Test
    void get_event_by_id_test() {
        MotivationEventSimpleDao events = new MotivationEventSimpleDao();
        Assertions.assertEquals("сделать что-то", events.getEventById(1).getDescription());
    }

    @Test
    void get_motivation_events_test() {
        MotivationEventSimpleDao defaultEvents = new MotivationEventSimpleDao();
        LinkedList<MotivationEvent> expectedEvents = new LinkedList<>();
        expectedEvents.add(new MotivationEvent(1,"сделать что-то",5,5));
        expectedEvents.add(new MotivationEvent(2,"сделать что-то еще полезное",10,0));
        Assertions.assertEquals(expectedEvents.get(0), defaultEvents.getMotivationEvents().get(0));
    }

    @Test
    void constructor_with_params_test(){
        ArrayList<MotivationEvent> testEventsList = new ArrayList<>();
        MotivationEvent testEvent1 = new MotivationEvent(1,"Выгуляна собака",5,10);
        MotivationEvent testEvent2 = new MotivationEvent(2,"Сделано домашнее задание",10,10);
        testEventsList.add(testEvent1);
        testEventsList.add(testEvent2);
        MotivationEventSimpleDao events = new MotivationEventSimpleDao(testEventsList);
        Assertions.assertEquals(testEvent2,events.getEventById(2));
    }

    @Test
    void if_get_missing_id_throws_exception(){
        MotivationEventSimpleDao events = new MotivationEventSimpleDao();
        Assertions.assertThrows(NoSuchElementException.class,()->events.getEventById(3));
    }
}