package ru.iteco.account.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BankBookErrorDto {

    private String status;
    private Integer idNotFound;
    private String message;

    public BankBookErrorDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
