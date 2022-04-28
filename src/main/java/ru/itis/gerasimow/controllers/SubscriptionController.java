package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.services.AccountService;
import ru.itis.gerasimow.services.SubscriptionService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	private final AccountService accountService;

	@GetMapping
	public String getSubsPage(HttpSession session, Model model) {
		AccountDto user = (AccountDto) session.getAttribute("user");

		List<AccountDto> subs = subscriptionService.getSubscriptionsByUserItselfId(user.getId()).stream()
				.map(sub -> accountService.getAccountById(sub.getUserToSubscribe()))
				.collect(Collectors.toList());

		model.addAttribute("subs", subs);

		return "subsList";
	}
}
