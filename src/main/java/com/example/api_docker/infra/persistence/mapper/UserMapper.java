package com.example.api_docker.infra.persistence.mapper;

import com.example.api_docker.infra.persistence.entity.UserJpaEntity;
import com.example.api_docker.user.entity.User;

public class UserMapper {

    public static User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getName()
        );
    }

    public static UserJpaEntity toJpa(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        return entity;
    }
}
