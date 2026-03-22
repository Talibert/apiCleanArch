package com.example.api_docker.infra.persistence.repository;

import com.example.api_docker.infra.persistence.entity.UserJpaEntity;
import com.example.api_docker.infra.persistence.mapper.UserMapper;
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
        UserJpaEntity entity = UserMapper.toJpa(user);
        jpaRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        UserJpaEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toDomain(entity);
    }
}
