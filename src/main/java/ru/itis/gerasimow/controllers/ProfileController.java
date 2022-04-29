package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.SubscriptionDto;
import ru.itis.gerasimow.services.AccountService;
import ru.itis.gerasimow.services.LikeService;
import ru.itis.gerasimow.services.PostService;
import ru.itis.gerasimow.services.SubscriptionService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final PostService postService;

	private final AccountService accountService;

	private final SubscriptionService subscriptionService;

	private final LikeService likeService;

	@GetMapping
	public String getProfilePage(Model model, HttpSession session) {
		AccountDto user = (AccountDto) session.getAttribute("user");
		model.addAttribute("posts", postService.getAllByUserId(user.getId()));

		long amountOfSubs = subscriptionService.getSubscriptionsByUserToSubscribeId(user.getId()).size();

		model.addAttribute("amountOfSubs", amountOfSubs);

		return "profileWithHTML";
	}

	@GetMapping("/{profile-id}")
	public String getSomeoneProfile(@PathVariable("profile-id") Integer profileId, Model model, HttpSession session) {
		AccountDto user = (AccountDto) session.getAttribute("user");
		if (Objects.equals(user.getId(), profileId)) {
			return "redirect:/profile";
		}

		subscriptionService.getSubscriptionsByUserItselfId(user.getId()).stream()
				.filter(sub -> sub.getUserToSubscribe().equals(profileId))
				.findAny().ifPresent(subscriptionDto -> model.addAttribute("subscribed", "subscribed"));

		subscriptionService.getSubscriptionsByUserItselfId(profileId).stream()
				.filter(sub -> sub.getUserToSubscribe().equals(user.getId()))
				.findAny().ifPresent(sub -> model.addAttribute("userSubscribedToCurrentUser", user.getId()));

		long amountOfSubs = subscriptionService.getSubscriptionsByUserToSubscribeId(profileId).size();

		model.addAttribute("amountOfSubs", amountOfSubs);


		model.addAttribute("posts", postService.getAllByUserId(profileId));
		model.addAttribute("profileUser", accountService.getAccountById(profileId));
		return "someoneProfile";
	}
}
