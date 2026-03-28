package com.example.api_docker.user.usecase;

import com.example.api_docker.user.entity.User;
import com.example.api_docker.user.gateway.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateUserUseCaseTest {

    @Spy
    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute(){
        User user = User.builder().name("Guilherme").build();

        createUserUseCase.execute("Guilherme");

        Mockito.verify(userRepository).save(user);
    }
}