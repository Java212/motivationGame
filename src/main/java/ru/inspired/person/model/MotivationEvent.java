package ru.inspired.person.model;

public class MotivationEvent {
    private final int id; // id события
    private final String description; // описание события
    private final int bonus; // начисляем очки
    private final int fee; // отнимаем очки

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
