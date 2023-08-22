package ru.inspired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NamedQueries({
        @NamedQuery(name="selectAll", query = "select n from dailyStatus"),
        @NamedQuery(name="insert", query = "insert n from "),
})
public class DailyStatusDbDao implements DailyStatusDao {
    private  final EntityManager entityManager;

    @Autowired
    public DailyStatusDbDao(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<DailyStatus> getDailyStatuses(){
        return entityManager.createNamedQuery("selectAll").getResultList();
    }

    @Override
   public void saveDailyStatuses(List<DailyStatus> statuses){
        entityManager.createNamedQuery("insert");
    }
}
