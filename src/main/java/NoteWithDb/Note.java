package NoteWithDb;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name="notes")

@NamedQueries({
        @NamedQuery(name="selectAllNotes", query = "Select n from Note n"),
        @NamedQuery(name="selectById", query = "select n from Note n.id : numberId")
}
)
public class Note {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="note_id")
    private  Integer id;

    @Column(name="text")
    private  String text;

    @Column(name="data_time")
    private LocalDateTime createdTime;

    public Note(String text){
        this.text=text;
        createdTime=LocalDateTime.now();
    }

    public  Note(){
        createdTime=LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
