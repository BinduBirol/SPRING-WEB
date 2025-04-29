package com.birol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.birol.user.repo.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String contextPath = request.getContextPath(); // /spring

		HttpSession session = request.getSession();

		// Retrieve the username from the authentication object
		String username = authentication.getName();

		// Store the username in the session
		session.setAttribute("user", userRepository.findByUsername(username).get());

		response.sendRedirect(contextPath + "/user/home"); // --> /spring/user/home
	}
}
