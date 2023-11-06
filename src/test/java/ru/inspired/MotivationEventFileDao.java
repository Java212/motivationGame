package ru.inspired;

import ru.inspired.person.model.MotivationEvent;
import ru.inspired.person.MotivationEventDao;

import java.util.LinkedList;
import java.util.List;

public class MotivationEventFileDao implements MotivationEventDao {
    @Override
    public List<MotivationEvent> getMotivationEvents() {
        return new LinkedList<>(); //как-то прочитали из файла список объектов MotivationEvent
    }
    // getMotivationEvents() - возвращает пустой список new LinkedList<>(),
    // не выполняя чтение из файла. Это приводит к неправильному поведению.

    @Override
    public MotivationEvent getEventById(int id) {
        return new MotivationEvent(id,"some task",1,0);
    }

}
