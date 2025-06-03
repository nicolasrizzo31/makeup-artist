package com.nick.makeup_artist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.makeup_artist.model.ContactMessage;
import com.nick.makeup_artist.service.ContactMessageService;

@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

	private final ContactMessageService contactMessageService;

	@Autowired
	public ContactMessageController(ContactMessageService contactMessageService) {
		this.contactMessageService = contactMessageService;
	}

	@PostMapping
	public ResponseEntity<ContactMessage> saveContactMessage(@RequestBody ContactMessage contactMessage) {
		ContactMessage savedMessage = contactMessageService.saveContactMessage(contactMessage);
		return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
	}
}
