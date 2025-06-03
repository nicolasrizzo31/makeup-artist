package com.nick.makeup_artist.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nick.makeup_artist.model.ContactMessage;
import com.nick.makeup_artist.repository.ContactMessageRepository;

@Service
public class ContactMessageService {

	private final ContactMessageRepository contactMessageRepository;

	@Autowired
	public ContactMessageService(ContactMessageRepository contactMessageRepository) {
		this.contactMessageRepository = contactMessageRepository;
	}

	public ContactMessage saveContactMessage(ContactMessage contactMessage) {
		contactMessage.setSubmissionTimestamp(LocalDateTime.now());
		return contactMessageRepository.save(contactMessage);
	}
}

