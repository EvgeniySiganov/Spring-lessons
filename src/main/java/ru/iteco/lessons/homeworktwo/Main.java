package ru.iteco.lessons.homeworktwo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.iteco.lessons.homeworktwo.service.ExternalService;
import ru.iteco.lessons.homeworktwo.service.Flow;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalService externalService = applicationContext.getBean(ExternalService.class);
        externalService.getExternalInfo(3);

        Flow flow = applicationContext.getBean(Flow.class);
        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);

        applicationContext.close();


    }
}
