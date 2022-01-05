package ru.iteco.account.homeworkthree.service;

import org.springframework.stereotype.Component;
import ru.iteco.account.homeworkthree.controller.DoublingException;
import ru.iteco.account.homeworkthree.controller.NotFoundException;
import ru.iteco.account.homeworkthree.controller.OperationException;
import ru.iteco.account.homeworkthree.model.BankBookDto;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserServiceImpl implements UserService {

    private final Map<Integer, BankBookDto> mapBBDto = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init(){
        int id = sequenceId.getAndIncrement();
        mapBBDto.put(
                id,
                new BankBookDto(id, 1, "001", new BigDecimal(1000), "RUB"));
    }

    @Override
    public List<BankBookDto> findAllBankBookDtoByUserId(Integer userId) {
        List<BankBookDto> bankBookDtoList = new ArrayList<>();
        for (Map.Entry<Integer, BankBookDto> entry:
                mapBBDto.entrySet()) {
            if(Objects.equals(entry.getValue().getUserId(), userId)){
                bankBookDtoList.add(entry.getValue());
            }
        }
        if(bankBookDtoList.isEmpty()){
            throw new NotFoundException("USER NOT FOUND", userId);
        }
        return bankBookDtoList;
    }

    @Override
    public BankBookDto findBankBookDto(Integer bankBookId) {
        BankBookDto bankBookDto = mapBBDto.get(bankBookId);
        if(bankBookDto == null){
            throw new NotFoundException("BANK BOOK NOT FOUND", bankBookId);
        }
        return bankBookDto;
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        Integer id;
        for (Map.Entry<Integer, BankBookDto> entry:
                mapBBDto.entrySet()) {
            if(Objects.equals(entry.getValue().getUserId(), bankBookDto.getUserId()) &&
                Objects.equals(entry.getValue().getNumber(), bankBookDto.getNumber())){
                if(Objects.equals(entry.getValue().getCurrency(), bankBookDto.getCurrency())){
                    throw new DoublingException("BANK BOOK ALREADY EXIST");
                }
            }
        }
        id = sequenceId.getAndIncrement();
        bankBookDto.setId(id);
        mapBBDto.put(id, bankBookDto);
        return bankBookDto;
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        for (Map.Entry<Integer, BankBookDto> entry:
                mapBBDto.entrySet()) {
            if(Objects.equals(entry.getValue().getNumber(), bankBookDto.getNumber())){
                throw new OperationException("CANNOT BE CHANGED THE NUMBER OF BANK BOOK");
            }
        }
        mapBBDto.replace(bankBookDto.getId(), bankBookDto);
        return bankBookDto;
    }

    @Override
    public void deleteBankBook(Integer bankBookId) {
        mapBBDto.remove(bankBookId);
    }

    @Override
    public void deleteAllBankBookByUserId(Integer userId) {
        Map<Integer, BankBookDto> mapBBDtoTemp = new ConcurrentHashMap<>(mapBBDto);
        for (Map.Entry<Integer, BankBookDto> entry:
                mapBBDtoTemp.entrySet()) {
            if(Objects.equals(entry.getValue().getUserId(), userId)){
                mapBBDto.remove(entry.getKey());
            }
        }
    }
}
