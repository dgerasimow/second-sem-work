package ru.itis.gerasimow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.gerasimow.dto.SubscriptionDto;
import ru.itis.gerasimow.services.SubscriptionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class RestSubscriptionController {

	private final SubscriptionService subscriptionService;

	@PostMapping
	public SubscriptionDto subscribe(@RequestBody SubscriptionDto newSub) {
		return subscriptionService.subscribe(newSub);
	}

	@PostMapping("/unsubscribe")
	public void unsubscribe(@RequestBody SubscriptionDto subscriptionDto) {
		subscriptionService.unsubscribe(subscriptionDto);
	}
}
