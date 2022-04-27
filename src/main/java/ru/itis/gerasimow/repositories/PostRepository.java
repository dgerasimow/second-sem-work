package ru.itis.gerasimow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.gerasimow.models.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findAllByAccountId(Integer id);
}
