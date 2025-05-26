package com.nick.makeup_artist.repository;

import com.nick.makeup_artist.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	Optional<UserProfile> findFirstByOrderByIdAsc();
}
