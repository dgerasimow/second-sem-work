package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.services.PostService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	@GetMapping
	public String getPostsPage(Model model) {
		model.addAttribute("posts", postService.getAll());
		return "postsWithHTML";
	}
}
