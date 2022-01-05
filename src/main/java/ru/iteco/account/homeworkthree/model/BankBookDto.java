package ru.iteco.account.homeworkthree.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BankBookDto {
    Integer id;

    Integer userId;

    String number;

    BigDecimal amount;

    String currency;
}
