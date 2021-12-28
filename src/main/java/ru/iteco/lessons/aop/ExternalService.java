package ru.iteco.lessons.aop;

import org.springframework.stereotype.Component;

public interface ExternalService {
    public String getInfo(Long id);

    public String getCustomInfo();
}
