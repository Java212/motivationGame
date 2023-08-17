package ru.inspired.model;

import java.time.LocalDate;

public class DailyStatus {
    private final LocalDate date;

    private final int motivationEventId;
    private final CompletionState status;

    private final int calculationScore;

    public DailyStatus(MotivationEvent event, CompletionState state) {
        this.date = LocalDate.now();
        this.motivationEventId = event.getId();
        this.status = state;
        this.calculationScore = state == CompletionState.DONE ? event.getBonus() : event.getFee();
    }

    public DailyStatus(LocalDate date, int motivationEventId, CompletionState status, int calculationScore) {
        this.date = date;
        this.motivationEventId = motivationEventId;
        this.status = status;
        this.calculationScore = calculationScore;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getMotivationEventId() {
        return motivationEventId;
    }

    public int getCalculationScore() {
        return calculationScore;
    }

    public CompletionState getStatus() {
        return status;
    }
}
