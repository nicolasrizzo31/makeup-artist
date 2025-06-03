package com.nick.makeup_artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nick.makeup_artist.model.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
	// JpaRepository provides standard CRUD operations
	// No additional methods needed for now
}
