package ru.iteco.lessons.homeworkone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

@Component
public class ExternalServiceImpl implements ExternalService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);
    HashMap<Integer, ExternalInfo> mapInfo = new HashMap<>();
    @Value("${id-not-process}")
    private Integer id;

    public ExternalServiceImpl() {
        mapInfo.put(id, new ExternalInfo(id, "from properties"));
    }

    @CacheResult
    @PrintResult
    public ExternalInfo getExternalInfo(Integer id){
        LOGGER.info("Get ExternalInfo by id: {}", id);
        ExternalInfo externalInfo = mapInfo.get(id);
        if(externalInfo == null){
            throw new RuntimeException("Не найдено!");
        }
        return externalInfo;
    }


    @PostConstruct
    public void init(){
        mapInfo.put(1, new ExternalInfo(1,null));
        mapInfo.put(2, new ExternalInfo(2,"hasInfo"));
        mapInfo.put(3, new ExternalInfo(3,"info"));
        mapInfo.put(4, new ExternalInfo(4,"information"));

        LOGGER.info("MapInfo is created. Contain {} elements", mapInfo.size());
    }

    @PreDestroy
    public void destroy(){
        mapInfo.clear();
        LOGGER.info("MapInfo is cleared");
    }
}