package ru.iteco.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.dto.UserDto;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.StatusEntity;
import ru.iteco.account.model.entity.TransactionEntity;
import ru.iteco.account.model.entity.UserEntity;
import ru.iteco.account.repository.BankBookRepository;
import ru.iteco.account.repository.CurrencyRepository;
import ru.iteco.account.repository.StatusRepository;
import ru.iteco.account.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TransactionService {
    private final BankBookRepository bankBookRepository;
    private final TransactionRepository transactionRepository;
    private final StatusRepository statusRepository;

    public StatusEntity transaction(String numBBFrom, String numBBTo, BigDecimal amount){

        LocalDateTime localDateTimeInitiation = LocalDateTime.now();

        BankBookEntity bankBookEntityFrom = bankBookRepository.findByNumber(numBBFrom).orElseThrow(() ->
                new RuntimeException("NOT FOUND SENDER BANK BOOK BY NUMBER: " + numBBFrom));
        BankBookEntity bankBookEntityTo = bankBookRepository.findByNumber(numBBTo).orElseThrow(() ->
                new RuntimeException("NOT FOUND RECEIVER BANK BOOK BY NUMBER: " + numBBTo));

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setSourceBankBookId(bankBookEntityFrom.getId());
        transactionEntity.setTargetBankBookId(bankBookEntityTo.getId());
        transactionEntity.setAmount(amount);
        transactionEntity.setInitiationDate(localDateTimeInitiation);

        StatusEntity statusEntity;
        if(Objects.equals(bankBookEntityFrom.getCurrency(), bankBookEntityTo.getCurrency()) &&
            bankBookEntityFrom.getAmount().compareTo(amount) >= 0){
            statusEntity = statusRepository.findByName("successful");

            bankBookEntityFrom.setAmount(bankBookEntityFrom.getAmount().subtract(amount));
            bankBookRepository.save(bankBookEntityFrom);

            bankBookEntityTo.setAmount(bankBookEntityTo.getAmount().add(amount));
            bankBookRepository.save(bankBookEntityTo);

        }else {
            statusEntity = statusRepository.findByName("declined");
        }

        transactionEntity.setStatusEntity(statusEntity);
        transactionEntity.setCompletionDate(LocalDateTime.now());
        transactionRepository.save(transactionEntity);
        return statusEntity;
    }

}
