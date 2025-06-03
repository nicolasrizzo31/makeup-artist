package com.nick.makeup_artist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.makeup_artist.model.Booking;
import com.nick.makeup_artist.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		Booking createdBooking = bookingService.createBooking(booking);
		return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
	}
}
