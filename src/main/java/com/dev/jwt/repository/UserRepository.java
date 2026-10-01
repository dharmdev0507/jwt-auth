package com.dev.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.jwt.entity.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

	boolean existsByUsername(String username);

}
