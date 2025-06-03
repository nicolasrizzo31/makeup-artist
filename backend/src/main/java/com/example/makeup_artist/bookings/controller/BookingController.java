package com.example.makeup_artist.bookings.controller;

import com.example.makeup_artist.bookings.dto.BookingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/bookings")
// Allow requests from Angular dev server (default port 4200)
// This can be configured globally as well.
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingDTO bookingDto) {
        // Log the received DTO
        logger.info("Received booking request: {}", bookingDto.toString());

        // Here you would typically call a service to process and save the booking.
        // For now, just return a success message.
        // Example: bookingService.save(bookingDto);

        // Returning a plain text response as expected by the frontend's responseType: 'text'
        return ResponseEntity.ok("Booking request received successfully for " + bookingDto.getName());
    }
}
