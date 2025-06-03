package com.nick.makeup_artist.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users") // "user" is often a reserved keyword in SQL
public class User implements UserDetails {

	private static final long serialVersionUID = 7437138146349122376L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	private String roles; // Comma-separated roles, e.g., "ROLE_USER,ROLE_ADMIN"

	// UserDetails methods
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.roles == null || this.roles.isEmpty()) {
			return Collections.emptyList();
		}
		return Arrays.stream(this.roles.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	// getUsername() is provided by Lombok @Data
	// getPassword() is provided by Lombok @Data

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true; // Or use a field: private boolean enabled = true;
	}
}
