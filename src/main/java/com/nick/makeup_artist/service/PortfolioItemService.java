package com.nick.makeup_artist.service;

import com.nick.makeup_artist.model.PortfolioItem;
import com.nick.makeup_artist.repository.PortfolioItemRepository;
import com.nick.makeup_artist.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioItemService {

	@Autowired
	private PortfolioItemRepository portfolioItemRepository;

	@Transactional(readOnly = true)
	public List<PortfolioItem> getAllPortfolioItems() {
		return portfolioItemRepository.findAllByOrderByUploadDateDesc();
	}

	@Transactional(readOnly = true)
	public List<PortfolioItem> getPortfolioItemsByCategory(String category) {
		return portfolioItemRepository.findByCategory(category);
	}

	@Transactional(readOnly = true)
	public Optional<PortfolioItem> getPortfolioItemById(Long id) {
		return portfolioItemRepository.findById(id);
	}

	@Transactional
	public PortfolioItem savePortfolioItem(PortfolioItem item) {
		return portfolioItemRepository.save(item);
	}

	@Transactional
	public void deletePortfolioItem(Long id) {
		if (!portfolioItemRepository.existsById(id)) {
			throw new ResourceNotFoundException("PortfolioItem not found with id " + id);
		}
		portfolioItemRepository.deleteById(id);
	}
}
