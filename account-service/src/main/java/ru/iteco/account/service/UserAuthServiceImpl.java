package ru.iteco.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.iteco.account.model.dto.UserAuthDto;
import ru.iteco.account.model.entity.UserAuthEntity;
import ru.iteco.account.repository.UserAuthRepository;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public String create(UserAuthDto userAuthDto) {
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setUsername(userAuthDto.getLogin());
        userAuthEntity.setPassword(passwordEncoder.encode(userAuthDto.getPassword()));

        return userAuthRepository.save(userAuthEntity).getUsername();
    }
}
