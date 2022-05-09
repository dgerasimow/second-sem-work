package ru.itis.gerasimow.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String code;

	@OneToMany(mappedBy = "account")
	@ToString.Exclude
	private Set<Post> posts;

	@OneToMany(mappedBy = "account")
	@ToString.Exclude
	private Set<Comment> comments;

	@OneToMany(mappedBy = "account")
	@ToString.Exclude
	private Set<Like> likes;

	public enum Role {
		USER
	}

	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Account account = (Account) o;
		return id != null && Objects.equals(id, account.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
