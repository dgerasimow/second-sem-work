package ru.itis.gerasimow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.gerasimow.models.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
	private Integer id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	public static AccountDto from(Account account) {
		return AccountDto.builder()
				.firstName(account.getFirstName())
				.lastName(account.getLastName())
				.email(account.getEmail())
				.password(account.getPassword())
				.id(account.getId())
				.build();
	}
}
