package ru.iteco.lessons.homeworktwo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("allMethodInService()")
    public void beforeAllMethodInServiceAdvice(JoinPoint joinPoint){
        log.info("beforeAllMethodInServiceAdvice:: Start {}", joinPoint.getSignature().toShortString());
    }

    @Before("allMethodInService()")
    public void afterAllMethodInServiceAdvice(JoinPoint joinPoint){
        log.info("afterAllMethodInServiceAdvice:: END {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(value = "allMethodInService()", throwing = "exception")
    public void afterAllMethodInServiceTrowAdvice(JoinPoint joinPoint, Exception exception){
        log.info("afterAllMethodInServiceTrowAdvice:: END {} with THROW {}", joinPoint.getSignature().toShortString(),
                exception.toString());
    }

    @Pointcut("within(ru.iteco.lessons.homeworktwo.service.*)")
    public void allMethodInService(){}
}
