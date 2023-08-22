package ru.inspired;

import ru.inspired.model.CompletionStatus;
import ru.inspired.model.MotivationEvent;

import java.time.LocalDate;

public class DailyStatus {
    private final int motivationEventId;
    private final LocalDate date;
    private final CompletionStatus status;

    private final int calculationScore;

    public DailyStatus(MotivationEvent event, CompletionStatus state) {
        this.date = LocalDate.now();
        this.motivationEventId = event.getId();
        this.status = state;
        this.calculationScore = state == CompletionStatus.DONE ? event.getBonus() : event.getFee();
    }

    public DailyStatus(LocalDate date, int motivationEventId, CompletionStatus status, int calculationScore) {
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

    public CompletionStatus getStatus() {
        return status;
    }
}