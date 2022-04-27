package ru.itis.gerasimow.services;

import ru.itis.gerasimow.dto.PostDto;

import java.util.List;

public interface PostService {
	PostDto createPost(String text, Integer userId);

	List<PostDto> getAll();

	List<PostDto> getAllByUserId(Integer id);
}
