package ru.iteco.account.service;

import ru.iteco.account.model.dto.BankBookDto;

import java.util.List;

public interface BankBookUserService {
    List<BankBookDto> findAllBankBookDtoByUserId(Integer userId);
    BankBookDto findBankBookDto(Integer bankBookId);
    BankBookDto create(BankBookDto bankBookDto);
    BankBookDto update(BankBookDto bankBookDto);
    void deleteBankBook(Integer bankBookId);
    void deleteAllBankBookByUserId(Integer userId);

}
