package ru.itis.gerasimow.dto;

import lombok.Data;

@Data
public class NewCommentDto {
	private Integer postId;
	private Integer userId;
	private String text;
}
