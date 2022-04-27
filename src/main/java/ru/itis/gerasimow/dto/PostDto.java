package ru.itis.gerasimow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gerasimow.models.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
	private Integer id;

	private String text;

	private LocalDateTime date;

	private String firstName;

	private String lastName;

	private Integer userId;

	public static PostDto from(Post post) {
		return PostDto.builder()
				.userId(post.getAccount().getId())
				.text(post.getText())
				.id(post.getId())
				.date(post.getDate())
				.firstName(post.getAccount().getFirstName())
				.lastName(post.getAccount().getLastName())
				.build();
	}

	public static List<PostDto> from(List<Post> posts) {
		return posts.stream()
				.map(PostDto::from)
				.collect(Collectors.toList());
	}
}
