package ru.iteco.account.homeworkthree.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

    private String status;
    private Integer idNotFound;
    private String message;

    public ErrorDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
