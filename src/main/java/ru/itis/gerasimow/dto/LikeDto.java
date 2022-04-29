package ru.itis.gerasimow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gerasimow.models.Like;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto {
	private Integer id;

	private Integer userId;

	private Integer postId;

	public static LikeDto from(Like like) {
		return LikeDto.builder()
				.id(like.getId())
				.postId(like.getPost().getId())
				.userId(like.getAccount().getId())
				.build();
	}

	public static List<LikeDto> from(List<Like> likes) {
		return likes.stream()
				.map(LikeDto::from)
				.collect(Collectors.toList());
	}
}
