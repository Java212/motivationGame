package ru.inspired.person;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {

        //init spring beans
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory(); // создает объекты
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory); // считывает файл xml
        reader.loadBeanDefinitions(new ClassPathResource("config.xml"));

        //MotivationEventsChecker checker = factory.getBean("motivationChecker", MotivationEventsChecker.class);

        // getBean - достаем объект который нам нужен
        MotivationEventsChecker checker = (MotivationEventsChecker)factory.getBean("motivationChecker");

        //без использования спринга было бы:
//        MotivationEvent event1 = new MotivationEvent(1,"",0,1);
//        MotivationEvent event2 = new MotivationEvent(2,"",1,1);
//        List<MotivationEvent> list = List.of(event1,event2);
//        MotivationEventDao eventDao = new MotivationEventSimpleDao(list);
//        MotivationScoreCalc calc = new MotivationScoreCalc(0);
//        DailyLogProcessor processor = new DailyLogFileProcessor(eventDao);
//        то есть мы в коде жестко прописываем какие экземпляры создавать и куда их передавать как параметры
//        MotivationEventsChecker checker = new MotivationEventsChecker(eventDao,processor,calc);
        checker.run();

    }
}