package ru.iteco.lessons.aop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExternalRepository {

    public void serInfo(String info){
        log.info("Set info in ExternalRepository. Info: {}", info);
    }

    public String getUser(String user){
        if ("user".equals(user)) {
            return "EXTERNAL_INFO";
        }else {
            throw new RuntimeException("User not access");
        }
    }
}
