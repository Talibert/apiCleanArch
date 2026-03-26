package com.example.api_docker.user.usecase;

import com.example.api_docker.infra.exception.UserNotFoundException;
import com.example.api_docker.user.entity.User;
import com.example.api_docker.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public User execute(Long id, String name) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        User updatedUser = User.builder()
                .id(id)
                .name(name)
                .build();

        return userRepository.update(updatedUser);
    }
}
