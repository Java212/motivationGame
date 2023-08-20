package NoteWithDb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class NoteDb implements NoteRepository{
    private EntityManager entityManager;

    @Autowired
    public NoteDb(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<Note> getNotes(){
//        String jpql = "SELECT n FROM Note n";
//        TypedQuery<Note> query = entityManager.createQuery(jpql, Note.class);
        TypedQuery<Note> query = entityManager.createNamedQuery("selectAllNotes", Note.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addNotes(Note note){
        try {
            entityManager.persist(note);
        }catch (PersistenceException | DataIntegrityViolationException ex){
            System.out.println(ex);
        }
    }
    @Override
    @Transactional
    public List<Note> filtrNoteById(int id){
        TypedQuery<Note> query = entityManager.createNamedQuery("selectById", Note.class);
        return query.getResultList();
    }
}
