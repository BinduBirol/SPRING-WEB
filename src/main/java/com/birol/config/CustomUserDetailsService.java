package com.birol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.birol.user.entity.UserEntity;
import com.birol.user.entity.UserRoles;
import com.birol.user.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetch user from the database
		UserEntity userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		// Get the roles for the user
		List<SimpleGrantedAuthority> authorities = getRoles(userEntity);

		// Return the user with their granted authorities (roles)
		return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
	}

	/**
	 * This method returns a list of granted authorities based on the user's role.
	 * If the user has the ADMIN role, all roles (ADMIN, MODERATOR, USER, GUEST)
	 * will be granted. If the user has MODERATOR role, MODERATOR, USER, and GUEST
	 * roles will be granted. If the user has USER role, only the GUEST role will be
	 * granted.
	 * 
	 * @param userEntity the UserEntity object containing the user's data
	 * @return a list of SimpleGrantedAuthority objects representing the user's
	 *         roles
	 */
	private List<SimpleGrantedAuthority> getRoles(UserEntity userEntity) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		// Add the user's main role as authority
		authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name()));

		// Check if the user is an admin and grant all roles (ADMIN, MODERATOR, USER,
		// GUEST)
		if (userEntity.getRole() == UserRoles.ADMIN) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}
		// Check if the user is a moderator and grant MODERATOR, USER, and GUEST roles
		else if (userEntity.getRole() == UserRoles.MODERATOR) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}
		// If the user is a regular user, grant the GUEST role
		else if (userEntity.getRole() == UserRoles.USER) {
			authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}

		return authorities;
	}
}
