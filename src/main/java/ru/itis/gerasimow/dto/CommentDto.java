package ru.itis.gerasimow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gerasimow.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
	private Integer id;

	private String userFirstName;

	private String text;

	private Integer userId;

	public static CommentDto from(Comment comment) {
		return CommentDto.builder()
				.userFirstName(comment.getAccount().getFirstName())
				.id(comment.getId())
				.text(comment.getText())
				.userId(comment.getAccount().getId())
				.build();
	}

	public static List<CommentDto> from(List<Comment> comments) {
		return comments.stream()
				.map(CommentDto::from)
				.collect(Collectors.toList());
	}
}
