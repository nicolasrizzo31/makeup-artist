package com.nick.makeup_artist.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nick.makeup_artist.exception.ResourceNotFoundException;
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

	public List<ContactMessage> getAllContactMessages() {
		return contactMessageRepository.findAll();
	}

	public ContactMessage getContactMessageById(Long id) {
		return contactMessageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ContactMessage not found with id: " + id));
	}

	public void deleteContactMessage(Long id) {
		if (!contactMessageRepository.existsById(id)) {
			throw new ResourceNotFoundException("ContactMessage not found with id: " + id);
		}
		contactMessageRepository.deleteById(id);
	}
}

