package ru.iteco.account.model.dto;

import lombok.Data;

@Data
public class UserAuthDto {

    private String login;
    private String password;
}
