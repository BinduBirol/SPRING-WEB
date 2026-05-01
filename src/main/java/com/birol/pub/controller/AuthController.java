package com.birol.pub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.birol.user.entity.UserEntity;
import com.birol.user.entity.UserRoles;
import com.birol.user.repo.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// ------------------------
	// Login Page
	// ------------------------
	@GetMapping("/login")
	public String loginPage(Model model, HttpSession session) {

		String errorMessage = (String) session.getAttribute("loginError");

		if (errorMessage != null) {
			model.addAttribute("error", errorMessage);
			session.removeAttribute("loginError");
		}

		return "public/auth/login";
	}

	// ------------------------
	// Register Page
	// ------------------------
	@GetMapping("/register")
	public String registerPage() {
		return "public/auth/register";
	}

	// ------------------------
	// Forgot Password Page
	// ------------------------
	@GetMapping("/forgot-password")
	public String forgotPasswordPage() {
		return "public/auth/forgot-password";
	}

	// ------------------------
	// Register User
	// ------------------------
	@PostMapping("/register")
	public String registerUser(@ModelAttribute UserEntity user, RedirectAttributes redirectAttributes) {

		try {
			// Check existing user
			if (userRepository.findByUsername(user.getUsername()).isPresent()) {
				redirectAttributes.addFlashAttribute("error", "User already exists");
				return "redirect:/register";
			}

			// Encrypt password
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			// Ensure username consistency
			user.setUsername(user.getEmail());

			// Default role assignment
			if (user.getRole() == null) {
				user.setRole(UserRoles.USER);
			}

			userRepository.save(user);

			redirectAttributes.addFlashAttribute("success", "Registration successful! You can now log in.");

			return "redirect:/login";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Registration failed. Please try again.");
			return "redirect:/register";
		}
	}
}