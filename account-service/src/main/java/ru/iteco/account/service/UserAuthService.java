package ru.iteco.account.service;

import ru.iteco.account.model.dto.UserAuthDto;

public interface UserAuthService {
    String create(UserAuthDto userAuthDto);
}
