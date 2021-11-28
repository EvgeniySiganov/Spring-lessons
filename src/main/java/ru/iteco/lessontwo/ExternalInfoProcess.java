package ru.iteco.lessontwo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Lazy
@Component
public class ExternalInfoProcess implements Process{

    @Value("${id-not-process}")
    private Integer id;

    @Override
    public boolean run(ExternalInfo externalInfo) {
        if(Objects.equals(externalInfo.id, this.id)){
            log.info("ExternalInfoProcess.run return: false");
            return false;
        }
        log.info("ExternalInfoProcess.run return: true");
        return true;
    }
}
