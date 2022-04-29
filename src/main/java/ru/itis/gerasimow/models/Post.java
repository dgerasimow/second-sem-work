package ru.itis.gerasimow.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String text;

	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@OneToMany(mappedBy = "post")
	@ToString.Exclude
	private Set<Comment> comments;

	@ToString.Exclude
	@OneToMany(mappedBy = "post")
	private Set<Like> likes;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Post post = (Post) o;
		return id != null && Objects.equals(id, post.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
