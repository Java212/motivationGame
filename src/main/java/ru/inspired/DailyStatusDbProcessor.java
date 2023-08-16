package ru.inspired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.inspired.model.CompletionState;
import ru.inspired.model.DailyStatus;
import ru.inspired.model.MotivationEvent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Component
@Profile("file")
public class DailyStatusDbProcessor implements DailyStatusDao {

    static final String DATE_FORMAT = "dd.MM.yy";
    private static final String DEFAULT_FILE_NAME = "motivationLog.csv";
    private final MotivationEventDao motivationEventDao;

    @Autowired
    public DailyStatusDbProcessor(MotivationEventDao motivationEventDao) {
        this.motivationEventDao = motivationEventDao;
    }

    @Override
    public List<DailyStatus> getDailyStatuses() throws IOException {
        List<DailyStatus> list = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_FILE_NAME)){
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null;){
                DailyStatus log = mapDailyLog(dateFormat, line);
                list.add(log);
            }
        } catch (ParseException e) {
            throw new IOException(e);
        }
        return list;
    }

    public DailyStatus mapDailyLog(SimpleDateFormat dateFormat, String line) throws ParseException {
        String[] lineSplit = line.split(",");
        if(lineSplit.length!=3){
            throw new ParseException("Error in file",0);
        }
        MotivationEvent event = motivationEventDao.getEventById(Integer.parseInt(lineSplit[1].trim()));
        LocalDate date = Instant.ofEpochMilli(dateFormat.parse(lineSplit[0].trim()).getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        CompletionState status = CompletionState.valueOf(lineSplit[2].trim());
        return new DailyStatus(date,event,status);
    }

    @Override
    public void saveDailyStatuses(List<DailyStatus> statuses) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try (FileWriter writer = new FileWriter(DEFAULT_FILE_NAME, true)) {
            for (DailyStatus logEntry : statuses) {
                String dateStr = logEntry.getDate().format(dateTimeFormatter);
                writer.write(dateStr + "," + logEntry.getEvent().getId() + "," + logEntry.getStatus().name()+"\n");
            }
        }
    }
}
