package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.RegistrationDto;
import ru.itis.gerasimow.dto.ValidationDto;
import ru.itis.gerasimow.services.AccountService;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

	private final AccountService accountService;

	@GetMapping
	public String getRegistrationPage() {
		return "loginWITHWERSTKA";
	}

	@PostMapping
	public String registerUser(RegistrationDto form, Model model) {
		log.warn("register user");
		Optional<AccountDto> account = accountService.register(form);

		if (account.isEmpty()) {
			return "redirect:/registration";
		}

		return "redirect:/login";
	}

	@PostMapping(value = "/validation", produces = "application/json")
	@ResponseBody
	public ValidationDto validateUser(RegistrationDto registrationDto) {
		log.warn("validate data");
		return accountService.validateRegistration(registrationDto);
	}
}
