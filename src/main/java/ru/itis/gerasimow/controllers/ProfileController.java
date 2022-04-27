package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.services.PostService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final PostService postService;

	@GetMapping
	public String getProfilePage(Model model, HttpSession session) {
		AccountDto user = (AccountDto) session.getAttribute("user");
		model.addAttribute("posts", postService.getAllByUserId(user.getId()));
		return "profileWithHTML";
	}
}
