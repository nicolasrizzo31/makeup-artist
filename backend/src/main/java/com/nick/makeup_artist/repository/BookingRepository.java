package com.nick.makeup_artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nick.makeup_artist.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	// JpaRepository provides standard CRUD operations
	// No additional methods needed for now
}