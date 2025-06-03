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
import com.nick.makeup_artist.model.ServiceItem;
import com.nick.makeup_artist.service.ServiceItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

	@Autowired
	private ServiceItemService serviceItemService;

	@GetMapping
	public List<ServiceItem> getActiveServices() {
		return serviceItemService.getActiveServices();
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<ServiceItem> getAllServices() {
		return serviceItemService.getAllServices();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ServiceItem> getServiceItemById(@PathVariable Long id) {
		return serviceItemService.getServiceItemById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResourceNotFoundException("ServiceItem not found with id " + id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ServiceItem> createServiceItem(@Valid @RequestBody ServiceItem serviceItem) {
		ServiceItem savedItem = serviceItemService.saveServiceItem(serviceItem);
		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ServiceItem> updateServiceItem(@PathVariable Long id, @Valid @RequestBody ServiceItem serviceItemDetails) {
		return serviceItemService.getServiceItemById(id)
				.map(existingItem -> {
					existingItem.setName(serviceItemDetails.getName());
					existingItem.setDescription(serviceItemDetails.getDescription());
					existingItem.setDurationMinutes(serviceItemDetails.getDurationMinutes());
					existingItem.setPrice(serviceItemDetails.getPrice());
					existingItem.setActive(serviceItemDetails.isActive());
					ServiceItem updatedItem = serviceItemService.saveServiceItem(existingItem);
					return ResponseEntity.ok(updatedItem);
				})
				.orElseThrow(() -> new ResourceNotFoundException("ServiceItem not found with id " + id));
	}

	@PutMapping("/{id}/toggle-active")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ServiceItem> toggleServiceItemActiveStatus(@PathVariable Long id) {
		ServiceItem updatedItem = serviceItemService.toggleServiceItemActiveStatus(id);
		return ResponseEntity.ok(updatedItem);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteServiceItem(@PathVariable Long id) {
		serviceItemService.deleteServiceItem(id); // Service method throws ResourceNotFoundException if not found
		return ResponseEntity.noContent().build();
	}
}
