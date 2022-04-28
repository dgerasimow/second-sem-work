package ru.itis.gerasimow.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.gerasimow.security.details.AccountUserDetailsService;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccountUserDetailsService accountUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SavedRequestAwareAuthenticationSuccessHandler awareAuthenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http
//				.rememberMe()
//				.rememberMeParameter("rememberMe")
//				.tokenRepository(tokenRepository())
//				.tokenValiditySeconds(60 * 60 * 24 * 365)
//				.and()
				.authorizeRequests()
				.antMatchers("/registration").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/profile").authenticated()
				.antMatchers("/posts").authenticated()
				.antMatchers("/").permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/profile")
				.failureUrl("/login?reason=error")
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(awareAuthenticationSuccessHandler)
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?reason=logout")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and();
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

}
