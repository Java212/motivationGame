package ru.inspired.model;

import java.time.LocalDate;

public class DailyStatus {
    private final LocalDate date;
    private final MotivationEvent event;
    private final CompletionState status;

    public DailyStatus(MotivationEvent event, CompletionState status) {
        this.date = LocalDate.now();
        this.event = event;
        this.status = status;
    }

    public DailyStatus(LocalDate date, MotivationEvent event, CompletionState status) {
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

    public CompletionState getStatus() {
        return status;
    }
}
