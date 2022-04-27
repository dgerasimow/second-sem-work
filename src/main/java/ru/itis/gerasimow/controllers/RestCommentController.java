package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.gerasimow.dto.CommentDto;
import ru.itis.gerasimow.dto.NewCommentDto;
import ru.itis.gerasimow.services.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class RestCommentController {

	private final CommentService commentService;

	@PostMapping
	public CommentDto createComment(NewCommentDto newComment) {
		return commentService.createComment(newComment);
	}
}
