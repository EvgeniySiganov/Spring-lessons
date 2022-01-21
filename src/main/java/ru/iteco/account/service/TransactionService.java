package ru.iteco.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.model.dto.TransactionDto;
import ru.iteco.account.model.dto.UserDto;
import ru.iteco.account.model.entity.*;
import ru.iteco.account.repository.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TransactionService {
    private final BankBookRepository bankBookRepository;
    private final TransactionRepository transactionRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public StatusEntity bankBookTransaction(String numBBFrom, String numBBTo, BigDecimal amount){

        LocalDateTime localDateTimeInitiation = LocalDateTime.now();

        BankBookEntity bankBookEntityFrom = bankBookRepository.lockByNumber(numBBFrom).orElseThrow(() ->
                new RuntimeException("NOT FOUND SENDER BANK BOOK BY NUMBER: " + numBBFrom));
        BankBookEntity bankBookEntityTo = bankBookRepository.lockByNumber(numBBTo).orElseThrow(() ->
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


    @Transactional
    public StatusEntity userTransaction(Integer userIdFrom, Integer userIdTo, BigDecimal amount, String currency){

        UserEntity userEntityFrom = userRepository.findById(userIdFrom).orElseThrow(() ->
                new RuntimeException("NOT FOUND SENDER USER BY NUMBER: " + userIdFrom));
        UserEntity userEntityTo = userRepository.findById(userIdTo).orElseThrow(() ->
                new RuntimeException("NOT FOUND RECEIVER USER BY NUMBER: " + userIdTo));

        CurrencyEntity currencyEntity = currencyRepository.findByName(currency);

        BankBookEntity bankBookEntityFrom = userEntityFrom.getBankBookEntities()
                .stream()
                .filter((s) -> s.getCurrency().equals(currencyEntity))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NOT FOUND BANK BOOK SENDER"));

        BankBookEntity bankBookEntityTo = userEntityTo.getBankBookEntities()
                .stream()
                .filter((s) -> s.getCurrency().equals(currencyEntity))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NOT FOUND BANK BOOK RECEIVER"));

        return bankBookTransaction(bankBookEntityFrom.getNumber(), bankBookEntityTo.getNumber(), amount);
    }

}
