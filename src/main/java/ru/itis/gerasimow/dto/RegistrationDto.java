package ru.itis.gerasimow.dto;

import lombok.Data;

@Data
public class RegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
