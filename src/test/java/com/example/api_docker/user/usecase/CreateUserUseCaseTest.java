package com.example.api_docker.user.usecase;

import com.example.api_docker.user.gateway.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

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

    void testExecute(){

    }
}