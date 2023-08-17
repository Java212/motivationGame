package ru.inspired.notes;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Integer id;
    @Column(name = "text")
    private String text;
    @Column(name = "date_time")
    private final LocalDateTime createdTime;

    public Note(String text) {
        this.text = text;
        this.createdTime = LocalDateTime.now();
    }

    public Note(String text, LocalDateTime createdTime) {
        this.text = text;
        this.createdTime = createdTime;
    }

    Note() {
        createdTime = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
