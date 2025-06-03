package com.nick.makeup_artist.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
