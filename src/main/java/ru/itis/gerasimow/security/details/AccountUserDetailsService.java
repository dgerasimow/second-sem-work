package ru.itis.gerasimow.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.gerasimow.repositories.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountUserDetailsService implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new AccountUserDetails(
				accountRepository.findByEmailLike(email)
						.orElseThrow(
								() -> new UsernameNotFoundException("User not found")));
	}
}
