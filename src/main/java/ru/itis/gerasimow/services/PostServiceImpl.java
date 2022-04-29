package ru.itis.gerasimow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.gerasimow.dto.PostDto;
import ru.itis.gerasimow.models.Account;
import ru.itis.gerasimow.models.Post;
import ru.itis.gerasimow.repositories.AccountRepository;
import ru.itis.gerasimow.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final AccountRepository accountRepository;

	private final PostRepository postRepository;

	@Override
	public PostDto createPost(String text, Integer userId) {
		Account account = accountRepository.getById(userId);

		Post newPost = Post.builder()
				.date(LocalDateTime.now())
				.text(text)
				.account(account)
				.comments(Collections.emptySet())
				.likes(Collections.emptySet())
				.build();

		account.getPosts().add(newPost);

		Post savedPost = postRepository.save(newPost);

		return PostDto.from(savedPost);
	}

	@Override
	public List<PostDto> getAll() {
		return PostDto.from(postRepository.findAll());
	}

	@Override
	public List<PostDto> getAllByUserId(Integer id) {
		return PostDto.from(postRepository.findAllByAccountId(id));
	}
}
