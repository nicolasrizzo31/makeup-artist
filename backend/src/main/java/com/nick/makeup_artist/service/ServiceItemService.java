package com.nick.makeup_artist.service;

import com.nick.makeup_artist.model.ServiceItem;
import com.nick.makeup_artist.repository.ServiceItemRepository;
import com.nick.makeup_artist.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceItemService {

	@Autowired
	private ServiceItemRepository serviceItemRepository;

	@Transactional(readOnly = true)
	public List<ServiceItem> getActiveServices() {
		return serviceItemRepository.findByIsActiveTrueOrderByNameAsc();
	}

	@Transactional(readOnly = true)
	public List<ServiceItem> getAllServices() {
		return serviceItemRepository.findAllByOrderByNameAsc();
	}

	@Transactional(readOnly = true)
	public Optional<ServiceItem> getServiceItemById(Long id) {
		return serviceItemRepository.findById(id);
	}

	@Transactional
	public ServiceItem saveServiceItem(ServiceItem item) {
		return serviceItemRepository.save(item);
	}

	@Transactional
	public void deleteServiceItem(Long id) {
		if (!serviceItemRepository.existsById(id)) {
			throw new ResourceNotFoundException("ServiceItem not found with id " + id);
		}
		serviceItemRepository.deleteById(id);
	}

	@Transactional
	public ServiceItem toggleServiceItemActiveStatus(Long id) {
		ServiceItem item = serviceItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ServiceItem not found with id " + id));
		item.setActive(!item.isActive());
		return serviceItemRepository.save(item);
	}
}
