package com.nick.makeup_artist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.makeup_artist.model.UserProfile;
import com.nick.makeup_artist.service.UserProfileService;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@GetMapping
	public ResponseEntity<UserProfile> getCurrentUserProfile() {
		Optional<UserProfile> userProfileOpt = userProfileService.getUserProfile();
		return userProfileOpt.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfileDetails) {
		UserProfile updatedProfile = userProfileService.saveUserProfile(userProfileDetails);
		return ResponseEntity.ok(updatedProfile);
	}
}
