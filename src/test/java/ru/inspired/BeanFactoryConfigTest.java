package ru.inspired;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryConfigTest {

    // экземпляр логгера, используется для записи информации об ошибках и исключениях.
    // Логгер инициализируется с помощью LogManager и связывается с классом BeanFactoryConfigTest.
    Logger logger = LogManager.getLogger(BeanFactoryConfigTest.class);

    @Test
    void testConfig() { // проверяет правильность конфигурации бинов
        try{
            // DefaultListableBeanFactory - является реализацией интерфейса BeanFactory и предоставляет функциональность по созданию и управлению бинами.
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            // XmlBeanDefinitionReader - используется для чтения бинов из XML-файла.
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
            //  loadBeanDefinitions() - загружает определения бинов из файла "config.xml" и регистрирует их в фабрике бинов.
            reader.loadBeanDefinitions(new ClassPathResource("config.xml"));

            // получаем экземпляр класса из фабрики бинов
            MotivationEventsChecker checker = factory.getBean("motivationChecker", MotivationEventsChecker.class);
            Assertions.assertNotNull(checker);
        }
        catch (Exception e){
            logger.error(e);
            Assertions.fail();
        }

    }
}
