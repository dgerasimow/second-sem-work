package ru.itis.gerasimow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.RegistrationDto;
import ru.itis.gerasimow.dto.ValidationDto;
import ru.itis.gerasimow.models.Account;
import ru.itis.gerasimow.repositories.AccountRepository;

import java.util.HashMap;
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

	@Override
	public ValidationDto validateRegistration(RegistrationDto registrationDto) {
		HashMap<String, String> errors = new HashMap<>();
		errors.put("loginEmpty", "Логин не может быть пустым");
		errors.put("firstNameEmpty", "Имя не может быть пустым");
		errors.put("secondNameEmpty", "Фамилия не может быть пустым");
		errors.put("passwordEmpty", "Пароль не может быть пустым");
		errors.put("passwordRegexp", "Пароль должен быть не менее 6 символов, содержать одно число, спецсимвол, нижний и верхний регистр");

		ValidationDto validationDto = ValidationDto.builder()
				.errors(new HashMap<>())
				.build();

		HashMap<String, String> errorsToResponseData = new HashMap<>();

		if (registrationDto.getEmail() == null || registrationDto.getEmail().isEmpty()) {
			errorsToResponseData.put("loginEmpty", errors.get("loginEmpty"));
		}
		if (registrationDto.getPassword() == null || registrationDto.getPassword().isEmpty()) {
			errorsToResponseData.put("passwordEmpty", errors.get("passwordEmpty"));
		}
		if (registrationDto.getPassword() == null || !registrationDto.getPassword().matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")) {
			errorsToResponseData.put("passwordRegexp", errors.get("passwordRegexp"));
		}
		if (registrationDto.getPassword() == null || registrationDto.getFirstName().isEmpty()) {
			errorsToResponseData.put("firstNameEmpty", errors.get("firstNameEmpty"));
		}
		if (registrationDto.getLastName() == null || registrationDto.getLastName().isEmpty()) {
			errorsToResponseData.put("secondNameEmpty", errors.get("secondNameEmpty"));
		}
		if (!errorsToResponseData.isEmpty()) {
			validationDto.getErrors().put("errors", errorsToResponseData);
			validationDto.setSuccess(false);
		} else {
			validationDto.setSuccess(true);
		}

		return validationDto;
	}

}
