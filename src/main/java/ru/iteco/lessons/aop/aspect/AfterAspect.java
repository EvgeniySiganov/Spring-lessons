package ru.iteco.lessons.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AfterAspect {

    @AfterReturning(value = "allGetMethod()", returning = "result")
    public void afterGetInfoMethodAdvice(JoinPoint joinPoint, Object result){
        log.info("afterGetInfoMethodAdvice: After {} return {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(value = "allSaveMethod()", throwing = "exception")
    public void afterSaveMethodThrowAdvice(JoinPoint joinPoint, Exception exception){
        log.info("afterSaveMethodThrowAdvice: method {} return Exception: {}",
                joinPoint.getSignature().toShortString(),
                exception.toString());
    }

    @After("allSaveMethod() || allGetMethod()")
    public void afterAllSaveAndGetMethodAdvice(JoinPoint joinPoint){
        log.info("afterAllSaveAndGetMethodAdvice: Method {} with args: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    @Pointcut("execution(* get*(..))")
    public void allGetMethod(){}

    @Pointcut("execution(* save*(..))")
    public void allSaveMethod(){}


}
