package com.example.api_docker.infra.persistence.repository;

import com.example.api_docker.infra.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserJpaEntity, Long> {
}
