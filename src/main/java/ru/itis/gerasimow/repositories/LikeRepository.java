package ru.itis.gerasimow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.gerasimow.models.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	void deleteByAccountIdAndPostId(Integer userId, Integer postId);
	List<Like> findAllByPostId(Integer postId);
}
