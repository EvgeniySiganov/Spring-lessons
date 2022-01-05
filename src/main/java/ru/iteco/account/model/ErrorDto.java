package ru.iteco.account.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

    private String status;
    private Integer idNotFound;
    private String message;

}
