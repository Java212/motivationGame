package ru.inspired.model;

import java.time.LocalDateTime;

public class Note {


        private String text;
        private LocalDateTime dateTime;

        public Note() {
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public LocalDateTime getTime() {
            return dateTime.withNano(0);
        }


    public void setTime(LocalDateTime now) {
    }
}


