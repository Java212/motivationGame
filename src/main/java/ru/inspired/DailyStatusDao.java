package ru.inspired;

import ru.inspired.model.DailyStatus;

import java.io.IOException;
import java.util.List;

public interface DailyStatusDao {

    List<DailyStatus> getDailyStatuses() throws IOException;
    void saveDailyStatuses(List<DailyStatus> statuses) throws IOException;
}
