package ru.iteco.lessontwo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

@Component
public class ExternalServiceImpl implements ExternalService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);
    HashMap<Integer, ExternalInfo> mapInfo;

    @CacheResult
    public ExternalInfo getExternalInfo(Integer id){
        LOGGER.info("Get ExternalInfo by id: {}", id);
        return mapInfo.get(id);
    }


    @PostConstruct
    public void init(){
        this.mapInfo = new HashMap<>();
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