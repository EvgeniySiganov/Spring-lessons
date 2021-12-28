package ru.iteco.lessons.homeworktwo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.iteco.lessons.homeworkone.ExternalInfo;

@Component
@Slf4j
public class Flow {
    Process process;
    ExternalService externalService;

    public Flow(@Lazy Process process, ExternalService externalService) {
        this.process = process;
        this.externalService = externalService;
    }

    public void run (Integer id){
        ExternalInfo externalInfo = externalService.getExternalInfo(id);
        if(externalInfo.getInfo() != null){
            this.process.run(externalInfo);
        }
        else{
            log.info("ExternalInfoProcess.getClass = {}", this.process.getClass());
        }
    }
}
