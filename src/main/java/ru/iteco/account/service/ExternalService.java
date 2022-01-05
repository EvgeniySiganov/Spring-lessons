package ru.iteco.account.service;

import org.springframework.stereotype.Component;

@Component
public class ExternalService {

    public String getInfo(String id){
        return "INFO by " + id;
    }
}
