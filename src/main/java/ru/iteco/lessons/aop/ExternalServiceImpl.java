package ru.iteco.lessons.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iteco.lessons.aop.ExternalService;
import ru.iteco.lessons.aop.aspect.AspectEvent;

@Component
@Slf4j
public class ExternalServiceImpl implements ExternalService {
    @Override
    public String getInfo(Long id) {
        log.info("Call getInfo id {}", id);
        return "INFO";
    }

    @AspectEvent
    public String getCustomInfo() {
        log.info("Call getCustomInfo");
        return "CUSTOM_INFO";
    }
}
