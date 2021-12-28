package ru.iteco.lessons.aop;

import org.springframework.stereotype.Component;

@Component
public class ExternalServiceImplOther implements ExternalService {
    @Override
    public String getInfo(Long id) {
        return "OTHER_INFO id " + id;
    }

    @Override
    public String getCustomInfo() {
        return null;
    }
}
