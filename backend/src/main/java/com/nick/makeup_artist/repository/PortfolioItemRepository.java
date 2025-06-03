package com.nick.makeup_artist.repository;

import com.nick.makeup_artist.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
	List<PortfolioItem> findByCategory(String category);
	List<PortfolioItem> findAllByOrderByUploadDateDesc();
}
