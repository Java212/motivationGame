package ru.inspired.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motivationEvents")
public class MotivationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "motivationEvent_id")
    private  int id;
    @Column(name ="descriptionEvent")
    private  String description;
    private  int bonus;
    private  int fee;
    MotivationEvent() {
    }

    public MotivationEvent(int id, String description, int bonus, int fee) {
        this.id = id;
        this.description = description;
        this.bonus = bonus;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getBonus() {
        return bonus;
    }

    public int getFee() {
        return fee;
    }

}