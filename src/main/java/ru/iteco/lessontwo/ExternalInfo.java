package ru.iteco.lessontwo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy
public class ExternalInfo {
    Integer id;
    String info;

    public ExternalInfo(Integer id, String info) {
        this.id = id;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
