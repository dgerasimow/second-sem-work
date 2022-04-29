package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.gerasimow.dto.LikeDto;
import ru.itis.gerasimow.services.LikeService;

@RestController
@RequiredArgsConstructor
public class RestLikeController {

	private final LikeService likeService;

	@PostMapping("/like")
	public void like(@RequestBody LikeDto likeDto) {
		likeService.likePost(likeDto);
	}

	@PostMapping("/dislike")
	public void dislike(@RequestBody LikeDto likeDto) {
		likeService.dislikePost(likeDto);
	}
}
