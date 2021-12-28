package ru.iteco.lessons.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.iteco.lessons.aop.repository.ExternalRepository;
import ru.iteco.lessons.aop.repository.OtherRepository;
import ru.iteco.lessons.aop.repository.User;

@ComponentScan
@Slf4j
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class);
        ExternalService externalServiceImpl = annotationConfigApplicationContext.getBean("externalServiceImpl", ExternalService.class);
        ExternalService externalServiceImplOther = annotationConfigApplicationContext.getBean("externalServiceImplOther", ExternalService.class);

//        log.info("Result call externalServiceImpl.getInfo {}", externalServiceImpl.getInfo(1L));
//        log.info("Result call externalServiceImpl.getOtherInfo {}", externalServiceImpl.getCustomInfo());
//        log.info("Result call externalServiceImplOther.getInfo {}", externalServiceImplOther.getInfo(2L));

        ExternalRepository externalRepository = annotationConfigApplicationContext.getBean(ExternalRepository.class);
        String externalRepositoryInfo = externalRepository.getUser("user2");
        log.info("externalRepository.getInfo() return {}", externalRepositoryInfo);

        OtherRepository otherRepository = annotationConfigApplicationContext.getBean(OtherRepository.class);
        User user = new User(1, "user2");
        String otherRepositoryInfo = otherRepository.getInfo(user);

        log.info("otherRepository.getInfo() return {}", otherRepositoryInfo);

        otherRepository.saveInfo(null);
    }
}
