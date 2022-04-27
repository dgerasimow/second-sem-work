package ru.itis.gerasimow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.gerasimow.models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByEmailLike(String email);
}
