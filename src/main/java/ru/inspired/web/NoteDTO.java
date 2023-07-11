package ru.inspired.web;

public class NoteDTO { //Data Transfer Object
    private final String text;
    private final String createdDateTime;

    public NoteDTO(String text, String createdDateTime) {
        this.text = text;
        this.createdDateTime = createdDateTime;
    }

    public String getText() {
        return text;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }
}
