package com.example.api_docker.user.usecase;

import com.example.api_docker.user.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

class GetAllUsersUseCaseTest extends UserUseCaseAbstractTest{

    @Spy
    @InjectMocks
    private GetAllUsersUseCase getAllUsersUseCase;

    @Test
    void testExecute(){
        getAllUsersUseCase.execute();

        Mockito.verify(userRepository).findAll();
    }
}