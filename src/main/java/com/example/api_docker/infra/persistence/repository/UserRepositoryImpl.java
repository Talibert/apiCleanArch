package com.example.api_docker.infra.persistence.repository;

import com.example.api_docker.infra.persistence.entity.UserJpaEntity;
import com.example.api_docker.infra.persistence.mapper.UserMapper;
import com.example.api_docker.user.entity.User;
import com.example.api_docker.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserJpaEntity entity = userMapper.toJpa(user);
        UserJpaEntity saved = userRepositoryJpa.save(entity);

        return userMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryJpa.findById(id).map(u -> userMapper.toDomain(u));
    }

    @Override
    public List<User> findAll() {
        return userRepositoryJpa.findAll().stream().map(u -> userMapper.toDomain(u)).toList();
    }

    @Override
    public User update(User user) {
        UserJpaEntity entity = userMapper.toJpa(user);
        UserJpaEntity updated = userRepositoryJpa.save(entity);

        return userMapper.toDomain(updated);
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryJpa.deleteById(id);
    }
}
