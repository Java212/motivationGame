package ru.inspired;

import ru.inspired.model.CompletionStatus;
import ru.inspired.model.DailyLog;
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

public class DailyLogFileProcessor implements DailyLogProcessor {

    static final String DATE_FORMAT = "dd.MM.yy";
    private static final String DEFAULT_FILE_NAME = "motivationLog.csv";
    private final MotivationEventDao motivationEventDao;

    public DailyLogFileProcessor(MotivationEventDao motivationEventDao) {
        this.motivationEventDao = motivationEventDao;
    }

    @Override
    public List<DailyLog> getLog() throws IOException {
        List<DailyLog> list = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_FILE_NAME)){
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null;){
                DailyLog log = mapDailyLog(dateFormat, line);
                list.add(log);
            }
        } catch (ParseException e) {
            throw new IOException(e);
        }
        return list;
    }

    public DailyLog mapDailyLog(SimpleDateFormat dateFormat, String line) throws ParseException {
        String[] lineSplit = line.split(",");
        if(lineSplit.length!=3){
            throw new ParseException("Error in file",0);
        }
        MotivationEvent event = motivationEventDao.getEventById(Integer.parseInt(lineSplit[1].trim()));
        LocalDate date = Instant.ofEpochMilli(dateFormat.parse(lineSplit[0].trim()).getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        CompletionStatus status = CompletionStatus.valueOf(lineSplit[2].trim());
        return new DailyLog(date,event,status);
    }

    @Override
    public void reWriteLog(List<DailyLog> log) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try (FileWriter writer = new FileWriter(DEFAULT_FILE_NAME, false)) {
            for (DailyLog logEntry : log) {
                String dateStr = logEntry.getDate().format(dateTimeFormatter);
                writer.write(dateStr + "," + logEntry.getEvent().getId() + "," + logEntry.getStatus().name()+"\n");
            }
        }
    }
}
