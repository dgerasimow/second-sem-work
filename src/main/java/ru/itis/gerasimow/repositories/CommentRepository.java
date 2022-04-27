package ru.itis.gerasimow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.gerasimow.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
