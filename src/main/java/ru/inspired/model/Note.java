package ru.inspired.model;

import java.time.LocalDateTime;

public class Note {
    private final String text;
    private final LocalDateTime createdTime;

    public Note(String text) {
        this.text = text;
        this.createdTime = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public String getCreatedTimeAsStr() {
        return createdTime.format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
