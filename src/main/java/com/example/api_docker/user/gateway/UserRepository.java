package com.example.api_docker.user.gateway;

import com.example.api_docker.user.entity.User;

public interface UserRepository {
    void save(User user);
    User findById(Long id);
}
