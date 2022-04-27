package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.RegistrationDto;
import ru.itis.gerasimow.services.AccountService;

import java.util.Optional;

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
		Optional<AccountDto> account = accountService.register(form);

		if (account.isEmpty()) {
			return "redirect:/registration";
		}

		return "redirect:/login";
	}
}
