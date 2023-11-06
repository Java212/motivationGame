package ru.inspired.notes.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;



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