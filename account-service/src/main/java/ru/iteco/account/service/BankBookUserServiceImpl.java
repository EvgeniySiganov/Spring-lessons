package ru.iteco.account.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.CurrencyEntity;
import ru.iteco.account.model.exception.DoublingException;
import ru.iteco.account.model.exception.NotFoundException;
import ru.iteco.account.model.exception.OperationException;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.repository.BankBookRepository;
import ru.iteco.account.repository.CurrencyRepository;
import ru.iteco.account.mapper.BankBookMapper;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BankBookUserServiceImpl implements BankBookUserService {

    private final BankBookRepository bankBookRepository;
    private final CurrencyRepository currencyRepository;
    private final BankBookMapper bankBookMapper;


    @Override
    public List<BankBookDto> findAllBankBookDtoByUserId(Integer userId) {
        List<BankBookDto> bankBookDtoListFromRepo = bankBookRepository
                .findAllByUserId(userId)
                .stream()
                .map(bankBookMapper::mapToDto)
                .collect(Collectors.toList());

        if(bankBookDtoListFromRepo.isEmpty()){
            throw new NotFoundException("USER NOT FOUND", userId);
        }
        return bankBookDtoListFromRepo;
    }

    @Override
    public BankBookDto findBankBookDto(Integer bankBookId) {
        return bankBookRepository.findById(bankBookId)
                .map(bankBookMapper::mapToDto)
                .orElseThrow(() -> new NotFoundException("BANK BOOK NOT FOUND", bankBookId));
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        CurrencyEntity currencyEntity = currencyRepository.findByName(bankBookDto.getCurrency());
        Optional<BankBookEntity> bankBookEntityOptional = bankBookRepository
                .findAllByUserIdAndNumberAndCurrency(bankBookDto.getUserId(),
                        bankBookDto.getNumber(),
                        currencyEntity);
        if(bankBookEntityOptional.isPresent()){
            throw new DoublingException("BANK BOOK ALREADY EXIST");
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currencyEntity);

        return bankBookMapper.mapToDto(bankBookRepository.save(bankBookEntity));
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new NotFoundException("BANK BOOK NOT FOUND", bankBookDto.getId()));

        if(Objects.equals(bankBookEntity.getNumber(), bankBookDto.getNumber())){
            throw new OperationException("CANNOT BE CHANGED THE NUMBER OF BANK BOOK");
        }
        CurrencyEntity currencyEntity = currencyRepository.findByName(bankBookDto.getCurrency());

        bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currencyEntity);
        return bankBookMapper.mapToDto(bankBookRepository.save(bankBookEntity));
    }

    @Override
    public void deleteBankBook(Integer bankBookId) {
        bankBookRepository.deleteById(bankBookId);
    }

    @Override
    public void deleteAllBankBookByUserId(Integer userId) {
        bankBookRepository.deleteAllByUserId(userId);
    }
}
