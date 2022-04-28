package ru.itis.gerasimow.services;

import ru.itis.gerasimow.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
	SubscriptionDto subscribe(SubscriptionDto subscriptionDto);
	List<SubscriptionDto> getSubscriptionsByUserItselfId(Integer userId);
	List<SubscriptionDto> getSubscriptionsByUserToSubscribeId(Integer userId);
	void unsubscribe(SubscriptionDto subscriptionDto);
}
