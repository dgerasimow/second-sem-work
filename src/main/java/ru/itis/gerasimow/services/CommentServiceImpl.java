package ru.itis.gerasimow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.gerasimow.dto.CommentDto;
import ru.itis.gerasimow.dto.NewCommentDto;
import ru.itis.gerasimow.models.Account;
import ru.itis.gerasimow.models.Comment;
import ru.itis.gerasimow.models.Post;
import ru.itis.gerasimow.repositories.AccountRepository;
import ru.itis.gerasimow.repositories.CommentRepository;
import ru.itis.gerasimow.repositories.PostRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentRepository commentRepository;

	private final AccountRepository accountRepository;

	private final PostRepository postRepository;

	@Override
	public CommentDto createComment(NewCommentDto newCommentForm) {
		Account account = accountRepository.getById(newCommentForm.getUserId());

		Post post = postRepository.getById(newCommentForm.getPostId());

		Comment newComment = Comment.builder()
				.account(account)
				.date(LocalDateTime.now())
				.text(newCommentForm.getText())
				.post(post)
				.build();

		account.getComments().add(newComment);

		post.getComments().add(newComment);

		return CommentDto.from(commentRepository.save(newComment));
	}
}
