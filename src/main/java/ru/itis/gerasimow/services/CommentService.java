package ru.itis.gerasimow.services;

import ru.itis.gerasimow.dto.CommentDto;
import ru.itis.gerasimow.dto.NewCommentDto;

public interface CommentService {
	CommentDto createComment(NewCommentDto newComment);
}
