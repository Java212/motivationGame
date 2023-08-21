package ru.inspired;

import ru.inspired.model.DailyStatus;
import ru.inspired.model.DataRelatedException;

import java.util.List;

public interface DailyStatusDao {

    List<DailyStatus> getDailyStatuses(int userId) throws DataRelatedException;

    void saveDailyStatuses(List<DailyStatus> statuses) throws DataRelatedException;
}
