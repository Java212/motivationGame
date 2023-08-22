package ru.inspired;

import java.util.List;

public interface DailyStatusDao {

    List<DailyStatus> getDailyStatuses() ;

    void saveDailyStatuses(List<DailyStatus> statuses);
}