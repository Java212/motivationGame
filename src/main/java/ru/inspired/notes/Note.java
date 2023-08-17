package ru.inspired.notes;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "notes")
@NamedQueries(
        @NamedQuery(name = "notes.filterByDate", query = "select zzz from Note zzz where zzz.createdTime < :date")
)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(text, note.text) && Objects.equals(createdTime, note.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, createdTime);
    }
}
