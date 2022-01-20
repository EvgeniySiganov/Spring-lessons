package ru.iteco.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.iteco.account.model.dto.AddressDto;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.GroupEntity;
import ru.iteco.account.model.entity.UserEntity;
import ru.iteco.account.repository.GroupRepository;
import ru.iteco.account.repository.UserRepository;
import ru.iteco.account.service.AddressService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class AccountApplicationTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

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


}
