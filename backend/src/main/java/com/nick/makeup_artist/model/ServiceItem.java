package com.nick.makeup_artist.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_item")
public class ServiceItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;

	@NotNull
	@Min(1)
	@Column(nullable = false)
	private Integer durationMinutes;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private boolean isActive = true;

}
