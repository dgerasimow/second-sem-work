package ru.itis.gerasimow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.RegistrationDto;
import ru.itis.gerasimow.models.Account;
import ru.itis.gerasimow.repositories.AccountRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository accountRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<AccountDto> register(RegistrationDto form) {

		if (accountRepository.findByEmailLike(form.getEmail()).isPresent()) {
			return Optional.empty();
		}

		Account newAccount = Account.builder()
				.email(form.getEmail())
				.firstName(form.getFirstName())
				.lastName(form.getLastName())
				.password(passwordEncoder.encode(form.getPassword()))
				.role(Account.Role.USER)
				.build();

		return Optional.of(AccountDto.from(accountRepository.save(newAccount)));
	}

	@Override
	public AccountDto getAccountById(Integer id) {
		return AccountDto.from(accountRepository.getById(id));
	}
}
