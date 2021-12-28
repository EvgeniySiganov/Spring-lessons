package ru.iteco.lessons.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class LoggingAspect {

    @Before("allGetAdvice() && allSetAdvice()")
    public void beforeAllGetInfoAdvice(){
        log.info("beforeGetInfoAdvice: try call method getAllInfo()");
    }

    @Pointcut("execution(public String get*(..))")
    public void allGetAdvice(){

    }

    @Pointcut("execution(public * set*())")
    public void allSetAdvice(){

    }
}
