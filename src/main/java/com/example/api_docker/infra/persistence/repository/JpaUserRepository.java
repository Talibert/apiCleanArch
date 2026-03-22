package com.example.api_docker.infra.persistence.repository;

import com.example.api_docker.infra.persistence.entity.UserJpaEntity;
import com.example.api_docker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserJpaEntity, Long> {
}
