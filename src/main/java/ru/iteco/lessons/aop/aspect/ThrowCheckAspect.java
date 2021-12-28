package ru.iteco.lessons.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ThrowCheckAspect {

    @Around("allMethodInRepo()")
    public Object aroundAllMethodThrow(ProceedingJoinPoint proceedingJoinPoint){
        log.info("aroundAllMethodThrow: START METHOD {}", proceedingJoinPoint.getSignature().toShortString());

        try{
            return proceedingJoinPoint.proceed();
        }catch(Exception e){
            log.error("Exception proceed! Return NULL!", e);
            return null;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }finally {
            log.info("aroundAllMethodThrow: END METHOD: {}", proceedingJoinPoint.getSignature().toShortString());
        }
    }

    @Pointcut("within(ru.iteco.lessons.aop..*)")
    public void allMethodInRepo(){}
}
