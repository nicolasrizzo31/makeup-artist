package com.nick.makeup_artist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nick.makeup_artist.model.UserProfile;
import com.nick.makeup_artist.repository.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Transactional(readOnly = true)
	public Optional<UserProfile> getUserProfile() {
		return userProfileRepository.findFirstByOrderByIdAsc();
	}

	@Transactional
	public UserProfile saveUserProfile(UserProfile userProfileDetails) {
		Optional<UserProfile> existingProfileOpt = userProfileRepository.findFirstByOrderByIdAsc();

		UserProfile profileToSave;
		if (existingProfileOpt.isPresent()) {
			profileToSave = existingProfileOpt.get();
			// Update existing profile fields
			profileToSave.setBio(userProfileDetails.getBio());
			profileToSave.setFullName(userProfileDetails.getFullName());
			profileToSave.setEmail(userProfileDetails.getEmail());
			profileToSave.setPhoneNumber(userProfileDetails.getPhoneNumber());
			profileToSave.setInstagramHandle(userProfileDetails.getInstagramHandle());
			profileToSave.setFacebookProfileUrl(userProfileDetails.getFacebookProfileUrl());
		} else {
			// No existing profile, create a new one
			profileToSave = new UserProfile();
			profileToSave.setBio(userProfileDetails.getBio());
			profileToSave.setFullName(userProfileDetails.getFullName());
			profileToSave.setEmail(userProfileDetails.getEmail());
			profileToSave.setPhoneNumber(userProfileDetails.getPhoneNumber());
			profileToSave.setInstagramHandle(userProfileDetails.getInstagramHandle());
			profileToSave.setFacebookProfileUrl(userProfileDetails.getFacebookProfileUrl());
			// Explicitly set ID to null if we want the DB to generate it for a new entity,
			// or manage a fixed ID if we want to ensure only one.
			// For now, allowing auto-generation.
		}
		return userProfileRepository.save(profileToSave);
	}
}
