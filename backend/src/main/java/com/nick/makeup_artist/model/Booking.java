package com.nick.makeup_artist.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotBlank
	@Email
	@Column(nullable = false)
	private String email;

	private String phone;

	@NotNull
	@FutureOrPresent
	@Column(nullable = false)
	private LocalDate bookingDate;

	@NotNull
	@Column(nullable = false)
	private LocalTime bookingTime;

	@NotBlank
	@Column(nullable = false)
	private String serviceRequested;

	@Column(length = 1000)
	private String message;

	private LocalDateTime submissionTimestamp;
}