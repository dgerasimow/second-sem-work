package ru.itis.gerasimow.services;

import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.RegistrationDto;

import java.util.Optional;

public interface AccountService {
	Optional<AccountDto> register(RegistrationDto form);
	AccountDto getAccountById(Integer id);
}
