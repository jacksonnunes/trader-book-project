package com.traderbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsersDetailsService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/home").authenticated()
				.antMatchers("/users/**", "/methods/**").hasAuthority("USER_ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll()
				.and()
			.csrf().disable();
		
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//			.withUser("admin").password("{noop}admin").roles("ADMIN")
//			.and().withUser("user").password("{noop}user").roles("USER");
//	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}
