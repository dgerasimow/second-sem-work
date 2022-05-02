package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.gerasimow.dto.LikeDto;
import ru.itis.gerasimow.dto.PostDto;
import ru.itis.gerasimow.services.LikeService;
import ru.itis.gerasimow.services.PostService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	private final LikeService likeService;

	@GetMapping
	public String getPostsPage(Model model) {
		List<PostDto> posts = postService.getAll().stream()
				.peek(postDto -> {
					Optional<LikeDto> like = likeService.getAllByPostId(postDto.getId()).stream()
							.filter(likeDto -> likeDto.getPostId().equals(postDto.getId()))
							.findAny();

					postDto.setIsLiked(like.isPresent());
				}).collect(Collectors.toList());

		model.addAttribute("posts", posts);
		return "postsWithHTML";
	}
}
