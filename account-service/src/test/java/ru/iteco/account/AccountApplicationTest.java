package ru.iteco.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.iteco.account.model.currency.ConverterRequest;
import ru.iteco.account.model.dto.AddressDto;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.GroupEntity;
import ru.iteco.account.model.entity.UserEntity;
import ru.iteco.account.repository.BankBookRepository;
import ru.iteco.account.repository.GroupRepository;
import ru.iteco.account.repository.UserRepository;
import ru.iteco.account.service.AddressService;
import ru.iteco.account.service.CurrencyServiceApi;
import ru.iteco.account.service.StockServiceApi;
import ru.iteco.account.service.TransactionService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class AccountApplicationTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private BankBookRepository bankBookRepository;

    @Autowired
    private CurrencyServiceApi currencyServiceApi;

    @Autowired
    private StockServiceApi stockServiceApi;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testLoadUserFromAddress(){
        AddressDto addressDto = AddressDto.builder().country("RUSSIA").city("OMSK").street("LENINA")
                .home("5").build();
        UserEntity user = addressService.getUserByAddress(addressDto);
        log.info(String.valueOf(user));
    }

    @Test
    @Transactional()
    void testLoadUserById(){
        Optional<UserEntity> userEntityOptional = userRepository.findById(1);
        UserEntity user = userEntityOptional.get();
        log.info("NOT ADDRESS");
        log.info("ADDRESS: {}", user.getAddress().toString());

        List<BankBookEntity> bankBookEntityList = user.getBankBookEntities();
        log.info("BANK BOOK: {}", bankBookEntityList);
    }

    @Test
    @Transactional
    void testLoadManyGroups(){
        UserEntity userEntity = userRepository.findById(1).get();
        log.info("USER ID: {} with GROUPS: {}", userEntity.getId(), userEntity.getGroupEntities());
    }

    @Test
    @Transactional
    void testLoadUsersByGroups(){
        GroupEntity groupEntity = groupRepository.findById(1).get();
        log.info("GROUP ID: {} with USERS: {}", groupEntity.getId(), groupEntity.getUserEntities());
    }

    @Test
    void testTransactional(){
        transactionService.bankBookTransaction("num-2", "num-1", new BigDecimal(55));
    }

    @Test
    @Transactional
    void testFirstCache(){
        BankBookEntity bankBookEntityFirst = bankBookRepository.getById(2);

        bankBookEntityFirst.setAmount(new BigDecimal(900));
        bankBookRepository.save(bankBookEntityFirst);

        BankBookEntity bankBookEntitySecond = bankBookRepository.getById(2);

        log.info("bankBookEntityFirst: {}, bankBookEntitySecond: {}", bankBookEntityFirst, bankBookEntitySecond);

    }

    @Test
    void testCallExchangeAllCurrencyService() {
        log.info("RESULT: {}", currencyServiceApi.getAllExchange());
    }

    @Test
    void testCallConvertCurrencyService() {
        log.info("RESULT: {}", currencyServiceApi.convert(ConverterRequest
                .builder()
                .from("RUB")
                .to("EUR")
                .amount(new BigDecimal(10))
                .build()));
    }

    @Test
    void testCallStockService() throws URISyntaxException {
        List<String> rates = new ArrayList<>();
        rates.add("AAPL");
        rates.add("TSLA");
        rates.add("MSFT");
        log.info("RESULT: {}", stockServiceApi.getActualRates(rates));
    }

    @Test
    void generatePassword(){
        log.info("PASS: {}", passwordEncoder.encode("iteco"));
    }

}
