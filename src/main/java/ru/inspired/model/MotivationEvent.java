package ru.inspired.model;

public class MotivationEvent {
    private final int id;
    private final String description;
    private final int bonus;
    private final int fee;

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
