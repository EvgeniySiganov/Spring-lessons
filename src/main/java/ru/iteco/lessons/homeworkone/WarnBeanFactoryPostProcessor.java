package ru.iteco.lessons.homeworkone;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
class WarnBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] names = configurableListableBeanFactory.getBeanDefinitionNames();

        for (String name: names) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);

            if(beanDefinition.isPrototype()){
                String beanName = beanDefinition.getBeanClassName();
                Class<?> beanClass = Class.forName(beanName);

                for (Method method: beanClass.getDeclaredMethods()){
                    if(method.isAnnotationPresent(CacheResult.class));
                    log.warn("BeanFactoryPostProcessor log: bean {) has annotation @CacheResult and @Scope is PROTOTYPE",
                            beanName);
                }
            }
        }
    }
}

