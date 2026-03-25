package com.example.api_docker.infra.persistence.mapper;

import com.example.api_docker.infra.persistence.entity.UserJpaEntity;
import com.example.api_docker.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getName());
    }

    public UserJpaEntity toJpa(User user) {
        UserJpaEntity entity = new UserJpaEntity(user.getName());
        entity.setId(user.getId());

        return entity;
    }
}
