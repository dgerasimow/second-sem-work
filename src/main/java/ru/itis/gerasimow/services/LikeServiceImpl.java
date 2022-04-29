package ru.itis.gerasimow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.gerasimow.dto.LikeDto;
import ru.itis.gerasimow.models.Account;
import ru.itis.gerasimow.models.Like;
import ru.itis.gerasimow.models.Post;
import ru.itis.gerasimow.repositories.AccountRepository;
import ru.itis.gerasimow.repositories.LikeRepository;
import ru.itis.gerasimow.repositories.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

	private final LikeRepository likeRepository;

	private final AccountRepository accountRepository;

	private final PostRepository postRepository;

	@Override
	public void likePost(LikeDto likeDto) {
		Post post = postRepository.getById(likeDto.getPostId());

		Account account = accountRepository.getById(likeDto.getUserId());

		Like like = Like.builder()
				.post(post)
				.account(account)
				.build();

		account.getLikes().add(like);
		post.getLikes().add(like);

		likeRepository.save(like);
	}

	@Transactional
	@Override
	public void dislikePost(LikeDto likeDto) {
		likeRepository.deleteByAccountIdAndPostId(likeDto.getUserId(), likeDto.getPostId());
	}

	@Override
	public List<LikeDto> getAllByPostId(Integer postId) {
		return LikeDto.from(likeRepository.findAllByPostId(postId));
	}
}
