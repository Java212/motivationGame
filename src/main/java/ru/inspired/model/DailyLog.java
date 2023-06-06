package ru.inspired.model;

import java.time.LocalDate;

public class DailyLog {
    LocalDate date;
    MotivationEvent event;
    CompletionStatus status;

    public DailyLog(MotivationEvent event, CompletionStatus status) {
        this.date = LocalDate.now();
        this.event = event;
        this.status = status;
    }

    public DailyLog(LocalDate date, MotivationEvent event, CompletionStatus status) {
        this.date = date;
        this.event = event;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public MotivationEvent getEvent() {
        return event;
    }

    public CompletionStatus getStatus() {
        return status;
    }
}
