package ru.inspired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import ru.inspired.model.MotivationEvent;


import java.util.List;
import java.util.NoSuchElementException;

@Disabled
public class MotivationEventSimpleDaoTest {

   static MotivationEventDao motivationEventDao;

   @BeforeAll
    static void beforeAll(){
       DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
       XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
       reader.loadBeanDefinitions(new ClassPathResource("config.xml"));
       motivationEventDao=factory.getBean("motivationEventDao", MotivationEventSimpleDao.class);
   }

   @Test
      void test_method_getMotivationEvents(){
      List<MotivationEvent> listEvents = motivationEventDao.getMotivationEvents();
      Assertions.assertFalse(listEvents.isEmpty());
   }

   @Test
      void test_methods_getDescription_and_getBonus(){
      String description = motivationEventDao.getEventById(2).getDescription();
      Assertions.assertEquals("Вечером сходил в тренажерный зал", description);
      int bonus = motivationEventDao.getEventById(3).getBonus();
      int bonusActual=10;
      Assertions.assertEquals(bonusActual, bonus);
   }

   @Test
       void test_Exception() throws NoSuchElementException{
       Assertions.assertThrows(NoSuchElementException.class,
               ()-> motivationEventDao.getEventById(5));
   }
}
