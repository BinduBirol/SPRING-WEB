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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
    public String showLoginForm(Model model, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		
		String errorMessage = (String) session.getAttribute("loginError");
	    if (errorMessage != null) {
	        model.addAttribute("error", errorMessage);
	        session.removeAttribute("loginError");
	    }
        return "public/auth/login"; // Path to login.html
    }

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "public/auth/register"; // Path to registration.html
	}

	// Forgot password page
	@GetMapping("/forgot-password")
	public String showForgotPasswordForm() {
		return "public/auth/forgot-password"; // Path to forgot-password.html
	}

	// Handle user registration with UserEntity
	@PostMapping("/register")
	public String registerUser(@ModelAttribute UserEntity user, RedirectAttributes redirectAttributes) {

		try {
			// Check if the username already exists
			if (userRepository.findByUsername(user.getUsername()).isPresent()) {
				redirectAttributes.addFlashAttribute("error", "User already exists");
				return "redirect:/register";
			}

			// Encode the user's password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			user.setUsername(user.getEmail());

			// Set the default role (if necessary, otherwise you can also allow the user to
			// select a role)
			if (user.getRole() == null) {
				user.setRole(UserRoles.USER); // Default to USER if no role is set
			}

			// Save the user to the database
			userRepository.save(user);

			redirectAttributes.addFlashAttribute("success", "Registration successful! You can now log in.");

			// Redirect to the login page after successful registration
			return "redirect:/login";
		} catch (Exception e) {			
			redirectAttributes.addFlashAttribute("error", e.getCause());
			e.printStackTrace();
		}

		return "redirect:/register";
	}

}
