package ru.itis.gerasimow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.gerasimow.models.Subscription;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
	List<Subscription> findAllByUserItself(Integer id);
	List<Subscription> findAllByUserToSubscribe(Integer id);
	void deleteByUserItselfAndAndUserToSubscribe(Integer userItself, Integer userToSubscribe);
}
