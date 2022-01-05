package ru.iteco.account.homeworkthree.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer id;
    List<BankBookDto> listBankBookDto;
}
