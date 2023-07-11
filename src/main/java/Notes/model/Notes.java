package Notes.model;


import java.time.LocalDateTime;

public class Notes  {
    private String text;
    private LocalDateTime time;

    public Notes() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

