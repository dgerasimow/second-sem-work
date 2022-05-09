package ru.itis.gerasimow.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.models.Account;
import ru.itis.gerasimow.repositories.AccountRepository;
import ru.itis.gerasimow.security.VkAuth;
import ru.itis.gerasimow.security.details.AccountUserDetails;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

	private final AccountRepository accountRepository;

	@GetMapping
	public String getLoginPage(@RequestParam(required = false, name = "reason") String reason
			, Model model
			, Authentication authentication) {
		if (authentication != null) {
			return "redirect:/profile";
		}

		if (reason != null && reason.equals("error")) {
			model.addAttribute("error", "error");
		}
		return "loginWITHWERSTKA";
	}

	@GetMapping("/vk")
	public String vkAuth(HttpSession session, @RequestParam("first_name") String firstName,
	                     @RequestParam("last_name") String lastName,
	                     @RequestParam("uid") String id) {

		Account account = Account.builder()
				.role(Account.Role.USER)
				.firstName(firstName)
				.lastName(lastName)
				.comments(Collections.emptySet())
				.likes(Collections.emptySet())
				.posts(Collections.emptySet())
				.code(id)
				.build();

		Authentication auth = new VkAuth(new AccountUserDetails(account));
		SecurityContextHolder.getContext().setAuthentication(auth);

		Optional<Account> byId = accountRepository.findByCode(id);

		if (byId.isPresent()) {
			session.setAttribute("user", AccountDto.from(byId.get()));
		} else {
			Account save = accountRepository.save(account);
			session.setAttribute("user", AccountDto.from(save));
		}

		return "redirect:/profile";
	}
}
