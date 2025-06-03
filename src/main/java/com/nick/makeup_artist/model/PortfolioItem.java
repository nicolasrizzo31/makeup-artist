package com.nick.makeup_artist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio_item")
public class PortfolioItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String title;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;

	@NotBlank
	@Column(nullable = false)
	private String imageUrl;

	private String category;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime uploadDate;
}
