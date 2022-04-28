package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.services.AccountService;
import ru.itis.gerasimow.services.PostService;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final PostService postService;

	private final AccountService accountService;

	@GetMapping
	public String getProfilePage(Model model, HttpSession session) {
		AccountDto user = (AccountDto) session.getAttribute("user");
		model.addAttribute("posts", postService.getAllByUserId(user.getId()));
		return "profileWithHTML";
	}

	@GetMapping("/{profile-id}")
	public String getSomeoneProfile(@PathVariable("profile-id") Integer profileId, Model model, HttpSession session) {
		AccountDto user = (AccountDto) session.getAttribute("user");
		if (Objects.equals(user.getId(), profileId)) {
			return "redirect:/profile";
		}

		model.addAttribute("posts", postService.getAllByUserId(profileId));
		model.addAttribute("profileUser", accountService.getAccountById(profileId));
		return "someoneProfile";
	}
}
