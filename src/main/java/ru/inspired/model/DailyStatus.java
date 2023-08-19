package ru.inspired.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "daily_statuses")
@SequenceGenerator(initialValue = 1, name = "daily_status_seq",
        sequenceName = "daily_statuses_id_seq", allocationSize = 1)
public class DailyStatus {

    @Column(name = "daily_status_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_status_seq")
    private Integer id;
    @Column(name= "date_of_check")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motivation_event_id", referencedColumnName = "motivation_event_id")
    private MotivationEvent motivationEvent;
    @Enumerated(EnumType.STRING)
    @Column(name = "completion_state")
    private CompletionState state;

    @Column(name = "calculated_score")
    private int calculationScore;

    DailyStatus() {
    }

    public DailyStatus(MotivationEvent event, CompletionState state) {
        this.date = LocalDate.now();
        this.motivationEvent = event;
        this.state = state;
        this.calculationScore = state == CompletionState.DONE ? event.getBonus() : event.getFee();
    }

    public DailyStatus(LocalDate date, int motivationEventId, CompletionState status, int calculatedScore) {
        MotivationEvent event = new MotivationEvent(motivationEventId,"test",calculatedScore,calculatedScore);
        this.motivationEvent = event;
        this.date = date;
        this.state = status;
        this.calculationScore = calculatedScore;
    }

    public int getMotivationEventId() {
        return motivationEvent.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MotivationEvent getMotivationEvent() {
        return motivationEvent;
    }

    public void setMotivationEvent(MotivationEvent motivationEvent) {
        this.motivationEvent = motivationEvent;
    }

    public CompletionState getState() {
        return state;
    }

    public void setState(CompletionState state) {
        this.state = state;
    }

    public int getCalculationScore() {
        return calculationScore;
    }

    public void setCalculationScore(int calculationScore) {
        this.calculationScore = calculationScore;
    }
}
