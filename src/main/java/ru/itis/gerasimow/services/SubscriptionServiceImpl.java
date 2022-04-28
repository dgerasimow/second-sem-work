package ru.itis.gerasimow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.gerasimow.dto.SubscriptionDto;
import ru.itis.gerasimow.models.Subscription;
import ru.itis.gerasimow.repositories.SubscriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{

	private final SubscriptionRepository subscriptionRepository;

	@Override
	public SubscriptionDto subscribe(SubscriptionDto subscriptionDto) {

		Subscription subscription = Subscription.builder()
				.userToSubscribe(subscriptionDto.getUserToSubscribe())
				.userItself(subscriptionDto.getUserItself())
				.build();

		return SubscriptionDto.from(subscriptionRepository.save(subscription));
	}

	@Override
	public List<SubscriptionDto> getSubscriptionsByUserItselfId(Integer userId) {
		return SubscriptionDto.from(subscriptionRepository.findAllByUserItself(userId));
	}

	@Override
	public List<SubscriptionDto> getSubscriptionsByUserToSubscribeId(Integer userId) {
		return SubscriptionDto.from(subscriptionRepository.findAllByUserToSubscribe(userId));
	}

	@Transactional
	@Override
	public void unsubscribe(SubscriptionDto subscriptionDto) {
		subscriptionRepository.deleteByUserItselfAndAndUserToSubscribe(subscriptionDto.getUserItself(),
				subscriptionDto.getUserToSubscribe());
	}
}
