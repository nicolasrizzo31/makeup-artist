package com.nick.makeup_artist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nick.makeup_artist.exception.ResourceNotFoundException;
import com.nick.makeup_artist.model.Booking;
import com.nick.makeup_artist.repository.BookingRepository;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;

	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public Booking createBooking(Booking booking) {
		booking.setSubmissionTimestamp(LocalDateTime.now());
		return bookingRepository.save(booking);
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Optional<Booking> getBookingById(Long id) {
		return bookingRepository.findById(id);
	}

	public Booking updateBooking(Long id, Booking bookingDetails) {
		Booking existingBooking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

		existingBooking.setName(bookingDetails.getName());
		existingBooking.setEmail(bookingDetails.getEmail());
		existingBooking.setPhone(bookingDetails.getPhone());
		existingBooking.setBookingDate(bookingDetails.getBookingDate());
		existingBooking.setBookingTime(bookingDetails.getBookingTime());
		existingBooking.setServiceRequested(bookingDetails.getServiceRequested());
		existingBooking.setMessage(bookingDetails.getMessage());
		// submissionTimestamp is not updated

		return bookingRepository.save(existingBooking);
	}

	public void deleteBooking(Long id) {
		if (!bookingRepository.existsById(id)) {
			throw new ResourceNotFoundException("Booking not found with id: " + id);
		}
		bookingRepository.deleteById(id);
	}
}
