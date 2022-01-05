package ru.iteco.account.homeworkthree.service;

import ru.iteco.account.homeworkthree.model.BankBookDto;

import java.util.List;

public interface UserService {
    List<BankBookDto> findAllBankBookDtoByUserId(Integer userId);
    BankBookDto findBankBookDto(Integer bankBookId);
    BankBookDto create(BankBookDto bankBookDto);
    BankBookDto update(BankBookDto bankBookDto);
    void deleteBankBook(Integer bankBookId);
    void deleteAllBankBookByUserId(Integer userId);

}
