package ru.iteco.lessons.homeworktwo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.iteco.lessons.homeworktwo.CheckRequest;
import ru.iteco.lessons.homeworkone.ExternalInfo;

@Slf4j
@Lazy
@Component
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    @Override
    @CheckRequest
    public Boolean run(ExternalInfo externalInfo) {
        if(externalInfo.getId() == id){
            log.info("ExternalInfoProcess.run return: false");
            throw new RuntimeException("ID-NOT-PROCESS");
        }
        log.info("ExternalInfoProcess.run return: true");
        return true;
    }
}
