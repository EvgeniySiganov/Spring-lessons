package ru.iteco.lessontwo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Flow {
    ExternalInfoProcess externalInfoProcess;
    ExternalServiceImpl externalServiceImpl;

    public Flow(@Lazy ExternalInfoProcess externalInfoProcess, ExternalServiceImpl externalServiceImpl) {
        this.externalInfoProcess = externalInfoProcess;
        this.externalServiceImpl = externalServiceImpl;
    }

    void run (Integer id){
        ExternalInfo externalInfo = externalServiceImpl.getExternalInfo(id);
        if(externalInfo.getInfo() != null){
            externalInfoProcess.run(externalInfo);
        }
        else{
            log.info("ExternalInfoProcess.getClass = {}", externalInfoProcess.getClass());
        }
    }
}
