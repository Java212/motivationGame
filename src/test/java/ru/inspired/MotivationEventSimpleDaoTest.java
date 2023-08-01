package ru.inspired;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import ru.inspired.model.MotivationEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MotivationEventSimpleDaoTest {
    @Test
    void test_that_the_class_was_created(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("events-test.xml"));

        List  <MotivationEvent> List = factory.getBean("events", ArrayList.class);

        MotivationEventSimpleDao motivationEvents = new MotivationEventSimpleDao(List);
        List<MotivationEvent> eventList =  motivationEvents.getMotivationEvents();
        Assertions.assertEquals(3,eventList.size());
    }

    @Test
    void test_that_getEventById_works(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("events-test.xml"));

        List  <MotivationEvent> List = factory.getBean("events", ArrayList.class);

        MotivationEventSimpleDao motivationEvents = new MotivationEventSimpleDao(List);
        MotivationEvent event = motivationEvents.getEventById(1);
        Assertions.assertEquals("Сделана утренняя зарядка",event.getDescription());
    }
    @Test
    void test_that_class_throws_an_exception_when_an_element_is_not_found_by_the_seam(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("events-test.xml"));

        List  <MotivationEvent> List = factory.getBean("events", ArrayList.class);

        MotivationEventSimpleDao motivationEvents = new MotivationEventSimpleDao(List);
        Assertions.assertThrows(NoSuchElementException.class,()->motivationEvents.getEventById(7));
    }

    @Test
    void test_that_the_class_is_initialized_with_list(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("events-test.xml"));

        List  <MotivationEvent> eventsList = factory.getBean("events", ArrayList.class);
        MotivationEventSimpleDao motivationEvents = new MotivationEventSimpleDao(eventsList);
        MotivationEvent event =  motivationEvents.getEventById(3);
        Assertions.assertEquals("Сделать домашнее задание",event.getDescription());
    }
}
