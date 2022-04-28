package ru.itis.gerasimow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gerasimow.models.Subscription;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDto {
	private Integer id;

	private Integer userItself;

	private Integer userToSubscribe;

	public static SubscriptionDto from(Subscription subscription) {
		return SubscriptionDto.builder()
				.id(subscription.getId())
				.userToSubscribe(subscription.getUserToSubscribe())
				.userItself(subscription.getUserItself())
				.build();
	}

	public static List<SubscriptionDto> from(List<Subscription> subscriptions) {
		return subscriptions.stream()
				.map(SubscriptionDto::from)
				.collect(Collectors.toList());
	}
}
