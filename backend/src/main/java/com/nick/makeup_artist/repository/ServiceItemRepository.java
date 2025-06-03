package com.nick.makeup_artist.repository;

import com.nick.makeup_artist.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
	List<ServiceItem> findByIsActiveTrueOrderByNameAsc();
	List<ServiceItem> findAllByOrderByNameAsc();
}
