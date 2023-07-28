package ru.inspired.model;

import java.time.LocalDateTime;

public class Note {
    private final String text;
    private final LocalDateTime createdTime;

    public Note(String text) {
        this.text = text;
        this.createdTime = LocalDateTime.now();
    }

    public Note(String text, LocalDateTime createdTime) {
        this.text = text;
        this.createdTime = createdTime;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
