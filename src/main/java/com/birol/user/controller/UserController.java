package com.birol.user.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/home")
	public String home(Model model, HttpSession session, Authentication auth) {
		// Get the authenticated user
		User principal = (User) auth.getPrincipal();

		return "home/userHome"; // Path to home.html
	}

	@GetMapping("/password/change")
	public String changePasswordHome(Model model, HttpSession session, Authentication auth) {
		// Get the authenticated user
		User principal = (User) auth.getPrincipal();

		return "userAccount/changePassword"; // Path to home.html
	}

	@GetMapping("/account/settings")
	public String accountSettings(Model model, HttpSession session, Authentication auth) {
		// Get the authenticated user
		User principal = (User) auth.getPrincipal();

		UserEntity user = userRepository.findByUsername(principal.getUsername()).get();

		model.addAttribute("user", user); // Example of adding user info to session

		return "userAccount/accountSetting"; // Path to home.html
	}

	@PostMapping("/account/settings/submit")
	public String accountSettingsSubmit(Model model, HttpSession session, Authentication auth,
			@ModelAttribute UserEntity userNew, RedirectAttributes redirectAttributes) {

		try {

			User principal = (User) auth.getPrincipal();

			UserEntity userExisting = userRepository.findByUsername(principal.getUsername()).get();

			if (userNew.getImageMultipart() != null && !userNew.getImageMultipart().isEmpty()) {
			    userExisting.setImage(userNew.getImageMultipart().getBytes());
			}

			userExisting.setFirstName(userNew.getFirstName());
			userExisting.setLastName(userNew.getLastName());
			userExisting.setEmail(userNew.getEmail());
			userExisting.setIsTwoFactorEnabled(userNew.getIsTwoFactorEnabled());

			userExisting = userRepository.save(userExisting);
			
			session.setAttribute("user", userExisting);
			
			redirectAttributes.addFlashAttribute("success", "Information updated!");

		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/user/account/settings";
	}
	
	@GetMapping("/profile-image/{userId}")
	public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long userId) {
	    Optional<UserEntity> optionalUser = userRepository.findById(userId);
	    
	    if (optionalUser.isPresent() && optionalUser.get().getImage() != null) {
	        byte[] image = optionalUser.get().getImage();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG); // or IMAGE_PNG depending on your images
	        return new ResponseEntity<>(image, headers, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}
