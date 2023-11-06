package ru.inspired.notes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("db")
public class NotesDbDao implements NotesDao {

    public static final Logger LOGGER = LogManager.getLogger(NotesDbDao.class);

    private final EntityManager entityManager;

    @Autowired
    public NotesDbDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Note> getNotes() {
        String jpql = "SELECT n FROM Note n";
        TypedQuery<Note> query = entityManager.createQuery(jpql, Note.class);
        return query.getResultList();

    }

    //non JPA way with Hibernate criteria API
    public List<Note> getNotesFilteredByDate(LocalDateTime dateTime) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Note> criteria = builder.createQuery(Note.class);
        Root<Note> root = criteria.from(Note.class);
        criteria.select(root);

        if(dateTime != null) {
            criteria.where(builder.and(
                    builder.lessThan(root.get(Note_.createdTime), dateTime),
                    builder.equal(root.get(Note_.text), "text of old note")));
        }else {
            criteria.where(builder.lessThan(root.get(Note_.createdTime), LocalDateTime.now()));
        }
        return  entityManager.createQuery(criteria).getResultList();
    }

    @Override
    @Transactional
    public void addNote(Note note) {
        try {
           entityManager.persist(note);
        } catch (PersistenceException | DataIntegrityViolationException ex) {
            LOGGER.info("Data collision with notes");
            // retry
            // or inform user
        }
    }
}
