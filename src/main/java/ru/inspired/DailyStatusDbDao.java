package ru.inspired;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.DataRelatedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("db")
public class DailyStatusDbDao implements DailyStatusDao{
    @Override
    public List<DailyStatus> getDailyStatuses() throws DataRelatedException {
        return new ArrayList<>();
    }

    @Override
    public void saveDailyStatuses(List<DailyStatus> statuses) throws DataRelatedException {

    }
}
