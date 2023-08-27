package ru.inspired.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotivationEvent that = (MotivationEvent) o;
        return id == that.id && bonus == that.bonus && fee == that.fee && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, bonus, fee);
    }
}
