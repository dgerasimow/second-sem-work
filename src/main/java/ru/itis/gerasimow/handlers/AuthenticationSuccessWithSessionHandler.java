package ru.itis.gerasimow.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itis.gerasimow.dto.AccountDto;
import ru.itis.gerasimow.repositories.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessWithSessionHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                    Authentication authentication) throws ServletException, IOException {
		request.getSession().setAttribute("user",
				AccountDto.from(accountRepository.findByEmailLike(authentication.getName()).get()));
		response.sendRedirect("/profile");
	}

}
