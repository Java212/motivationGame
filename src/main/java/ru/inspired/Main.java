package ru.inspired;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {

        //init spring beans
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("config.xml"));

        //MotivationEventsChecker checker = factory.getBean("motivationChecker", MotivationEventsChecker.class);
        MotivationEventsChecker checker = (MotivationEventsChecker)factory.getBean("motivationChecker");
        checker.run();

    }
}