package com.example.api_docker.infra.repository;

import com.example.api_docker.user.entity.User;
import com.example.api_docker.user.gateway.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class UserRepositoryPostgresql implements UserRepository {

    private final JpaUserRepository jpaRepository;

    @Override
    public void save(User user){
        jpaRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
