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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.makeup_artist.model.ContactMessage;
import com.nick.makeup_artist.service.ContactMessageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

	private final ContactMessageService contactMessageService;

	@Autowired
	public ContactMessageController(ContactMessageService contactMessageService) {
		this.contactMessageService = contactMessageService;
	}

	@PostMapping
	public ResponseEntity<ContactMessage> saveContactMessage(@Valid @RequestBody ContactMessage contactMessage) {
		ContactMessage savedMessage = contactMessageService.saveContactMessage(contactMessage);
		return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ContactMessage>> getAllContactMessages() {
		List<ContactMessage> messages = contactMessageService.getAllContactMessages();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ContactMessage> getContactMessageById(@PathVariable Long id) {
		ContactMessage message = contactMessageService.getContactMessageById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteContactMessage(@PathVariable Long id) {
		contactMessageService.deleteContactMessage(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
