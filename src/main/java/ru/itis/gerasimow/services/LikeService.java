package ru.itis.gerasimow.services;

import ru.itis.gerasimow.dto.LikeDto;

import java.util.List;

public interface LikeService {
	void likePost(LikeDto likeDto);
	void dislikePost(LikeDto likeDto);
	List<LikeDto> getAllByPostId(Integer postId);
}
