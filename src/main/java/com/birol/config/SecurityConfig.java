package com.birol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.requestMatchers("/login", "/login/**", "/register", "/forgot-password", "/reset-password/**",
						"/assets/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").permitAll().successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler) // Use custom failure handler
				.and().logout().permitAll()
				.and()
			    .sessionManagement()
			        .invalidSessionUrl("/login?expired");

		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService); // Set your custom UserDetailsService
		provider.setPasswordEncoder(passwordEncoder()); // Set password encoder (BCryptPasswordEncoder)
		return provider;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
