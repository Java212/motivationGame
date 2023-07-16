package ru.inspired.web;

public class NoteDto { //Data Transfer Object
    private final String text;
    private final String createdDateTime;

    public NoteDto(String text, String createdDateTime) {
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
