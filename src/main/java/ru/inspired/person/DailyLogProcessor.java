package ru.inspired.person;

import ru.inspired.person.model.DailyLog;

import java.io.IOException;
import java.util.List;

public interface DailyLogProcessor {

    List<DailyLog> getLog() throws IOException; // ежедневно записывался id нашего события.
    void reWriteLog(List<DailyLog> log) throws IOException; // перезаписываем файл, для добавления новых событий
}
