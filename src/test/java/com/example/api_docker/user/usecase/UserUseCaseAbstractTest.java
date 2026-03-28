package com.example.api_docker.user.usecase;

import com.example.api_docker.user.gateway.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class UserUseCaseAbstractTest {

    @Mock
    protected UserRepository userRepository;

}
