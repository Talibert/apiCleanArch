package com.example.api_docker.user.usecase;

import com.example.api_docker.user.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Optional;

class DeleteUserUseCaseTest extends UserUseCaseAbstractTest{

    @Spy
    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;

    @Test
    void testExecute(){
        Long id = 1L;

        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.ofNullable(Mockito.mock(User.class)));

        deleteUserUseCase.execute(id);

        Mockito.verify(userRepository).deleteById(id);
    }
}