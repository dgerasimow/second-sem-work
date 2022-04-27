package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.dto.PostDto;
import ru.itis.gerasimow.services.PostService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	@PostMapping
	public PostDto createPost(@RequestParam("text") String text, HttpSession session) {
		AccountDto user = (AccountDto) session.getAttribute("user");

		return postService.createPost(text, user.getId());
	}
}
