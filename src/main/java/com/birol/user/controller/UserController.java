package com.birol.user.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.birol.user.entity.UserEntity;
import com.birol.user.repo.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// ------------------------
	// Helper method
	// ------------------------
	private UserEntity getLoggedInUser(Authentication auth) {
		User principal = (User) auth.getPrincipal();
		return userRepository.findByUsername(principal.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));
	}

	// ------------------------
	// Home
	// ------------------------
	@GetMapping("/home")
	public String home(Model model, Authentication auth) {
		model.addAttribute("user", getLoggedInUser(auth));
		return "home/userHome";
	}

	// ------------------------
	// Change Password Page
	// ------------------------
	@GetMapping("/password/change")
	public String changePassword() {
		return "userAccount/changePassword";
	}

	// ------------------------
	// Account Settings Page
	// ------------------------
	@GetMapping("/account/settings")
	public String accountSettings(Model model, Authentication auth) {
		model.addAttribute("user", getLoggedInUser(auth));
		return "userAccount/accountSetting";
	}

	// ------------------------
	// Update Account Settings
	// ------------------------
	@PostMapping("/account/settings/submit")
	public String updateAccountSettings(@ModelAttribute UserEntity userNew, Authentication auth, HttpSession session,
			RedirectAttributes redirectAttributes) {

		try {
			UserEntity user = getLoggedInUser(auth);

			// Update image if provided
			if (userNew.getImageMultipart() != null && !userNew.getImageMultipart().isEmpty()) {
				user.setImage(userNew.getImageMultipart().getBytes());
			}

			// Update fields
			user.setFirstName(userNew.getFirstName());
			user.setLastName(userNew.getLastName());
			user.setEmail(userNew.getEmail());
			user.setIsTwoFactorEnabled(userNew.getIsTwoFactorEnabled());

			UserEntity savedUser = userRepository.save(user);

			session.setAttribute("user", savedUser);
			redirectAttributes.addFlashAttribute("success", "Information updated successfully!");

		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("error", "Failed to update profile");
		}

		return "redirect:/user/account/settings";
	}

	// ------------------------
	// Profile Image API
	// ------------------------
	@GetMapping("/profile-image/{userId}")
	public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long userId) {

		byte[] imageBytes;

		Optional<UserEntity> userOpt = userRepository.findById(userId);

		try {
			if (userOpt.isPresent() && userOpt.get().getImage() != null) {
				imageBytes = userOpt.get().getImage();
			} else {
				ClassPathResource imgFile = new ClassPathResource("static/assets/img/user-avater.jpg");
				imageBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}