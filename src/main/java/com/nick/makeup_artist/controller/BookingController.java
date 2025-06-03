package com.nick.makeup_artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.makeup_artist.exception.ResourceNotFoundException;
import com.nick.makeup_artist.model.Booking;
import com.nick.makeup_artist.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
		Booking createdBooking = bookingService.createBooking(booking);
		return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
		Booking booking = bookingService.getBookingById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
		return ResponseEntity.ok(booking);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @Valid @RequestBody Booking bookingDetails) {
		Booking updatedBooking = bookingService.updateBooking(id, bookingDetails);
		return ResponseEntity.ok(updatedBooking);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
		return ResponseEntity.noContent().build();
	}
}
