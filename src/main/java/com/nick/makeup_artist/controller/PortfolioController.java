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
import com.nick.makeup_artist.model.PortfolioItem;
import com.nick.makeup_artist.service.PortfolioItemService;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

	@Autowired
	private PortfolioItemService portfolioItemService;

	@GetMapping
	public List<PortfolioItem> getAllPortfolioItems() {
		return portfolioItemService.getAllPortfolioItems();
	}

	@GetMapping("/category/{category}")
	public List<PortfolioItem> getPortfolioItemsByCategory(@PathVariable String category) {
		return portfolioItemService.getPortfolioItemsByCategory(category);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PortfolioItem> getPortfolioItemById(@PathVariable Long id) {
		return portfolioItemService.getPortfolioItemById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResourceNotFoundException("PortfolioItem not found with id " + id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PortfolioItem> createPortfolioItem(@RequestBody PortfolioItem portfolioItem) {
		PortfolioItem savedItem = portfolioItemService.savePortfolioItem(portfolioItem);
		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PortfolioItem> updatePortfolioItem(@PathVariable Long id, @RequestBody PortfolioItem portfolioItemDetails) {
		return portfolioItemService.getPortfolioItemById(id)
				.map(existingItem -> {
					existingItem.setTitle(portfolioItemDetails.getTitle());
					existingItem.setDescription(portfolioItemDetails.getDescription());
					existingItem.setImageUrl(portfolioItemDetails.getImageUrl());
					existingItem.setCategory(portfolioItemDetails.getCategory());
					// uploadDate is not updated here as it's a creation timestamp
					PortfolioItem updatedItem = portfolioItemService.savePortfolioItem(existingItem);
					return ResponseEntity.ok(updatedItem);
				})
				.orElseThrow(() -> new ResourceNotFoundException("PortfolioItem not found with id " + id));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletePortfolioItem(@PathVariable Long id) {
		portfolioItemService.deletePortfolioItem(id); // Service method throws ResourceNotFoundException if not found
		return ResponseEntity.noContent().build();
	}
}
