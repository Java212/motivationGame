package ru.inspired.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motivation_events")
public class MotivationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motivation_event_id")
    private  int id;
    @Column(name ="text")
    private  String description;
    private  int bonus;
    private  int fee;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            updatable = false
    )
    private User user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
