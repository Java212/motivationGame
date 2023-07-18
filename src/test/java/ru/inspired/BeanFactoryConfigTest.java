package ru.inspired;

import Notes.NotesController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryConfigTest {

    Logger logger = LogManager.getLogger(BeanFactoryConfigTest.class);

    @Test
    void testConfig() {
        try {
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
            reader.loadBeanDefinitions(new ClassPathResource("config.xml"));

            NotesController controller = factory.getBean("notesController", NotesController.class);
            Assertions.assertNotNull(controller);
        } catch (Exception e) {
            logger.error(e);
            Assertions.fail();
        }

    }
}
