package ru.iteco.lessons.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RepositoryAspect {

    @Before("within(ru.iteco.lessons.aop.repository.*)")
    public void beforeAllRepositoryAdvice(JoinPoint joinPoint){
        log.info("beforeAllRepositoryAdvice: try call {} with args {} in 'repository'",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }
}
