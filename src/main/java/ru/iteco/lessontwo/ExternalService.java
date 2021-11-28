package ru.iteco.lessontwo;

import org.springframework.stereotype.Component;

@Component
public interface ExternalService {
    ExternalInfo getExternalInfo(Integer id);
}
