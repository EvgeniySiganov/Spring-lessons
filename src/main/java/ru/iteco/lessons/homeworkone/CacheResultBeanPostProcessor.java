package ru.iteco.lessons.homeworkone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

@Component
public class CacheResultBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheResultBeanPostProcessor.class);
    HashMap<Method, Object> mapMethods = new HashMap<java.lang.reflect.Method, Object>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        Method[] methods = bean.getClass().getMethods();
//        LOGGER.info("postProcessAfterInitialisation: beanName: {}", beanName);
//        for (Method method: methods) {
//            if(method.isAnnotationPresent(CacheResult.class)){
//                LOGGER.info("Bean {} is proxy. Has annotation @CacheResult on method {}", beanName, method.getName());
//                ProxyFactory proxyFactory = new ProxyFactory(bean);
//                proxyFactory.addAdvice(new CacheResultMethodInterceptor());
//                proxyFactory.getProxy();
//            }
//        }

        return bean;
    }


}
