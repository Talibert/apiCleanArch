package com.example.api_docker.user.controller;

import com.example.api_docker.infra.config.SecurityConfig;
import com.example.api_docker.infra.exception.UserNotFoundException;
import com.example.api_docker.user.dto.request.CreateUserRequest;
import com.example.api_docker.user.entity.User;
import com.example.api_docker.user.usecase.*;
import com.example.api_docker.util.JsonBodyBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateUserUseCase createUserUseCase;

    @MockitoBean
    private GetUserByIdUseCase getUserByIdUseCase;

    @MockitoBean
    private GetAllUsersUseCase getAllUsersUseCase;

    @MockitoBean
    private UpdateUserUseCase updateUserUseCase;

    @MockitoBean
    private DeleteUserUseCase deleteUserUseCase;

    @Test
    @DisplayName("Vai criar um usuário e retornar 201")
    void testeCriaUsuario() throws Exception {
        User user = User.builder().id(1L).name("Guilherme").build();
        when(createUserUseCase.execute("Guilherme")).thenReturn(user);

        String body = JsonBodyBuilder.toJson(new CreateUserRequest("Guilherme"));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Guilherme"));
    }

    @Test
    @DisplayName("Vai retornar um 400 porque o nome está em branco")
    void testeCriaUsuarioNomeEmBranco() throws Exception {
        String body = JsonBodyBuilder.toJson(new CreateUserRequest(""));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"));

        verifyNoInteractions(createUserUseCase);
    }

    @Test
    @DisplayName("Vai retornar um 400 porque o nome está vazio")
    void testeCriaUsuarioNomeVazio() throws Exception {
        String body = JsonBodyBuilder.toJson(new CreateUserRequest(null));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"));

        verifyNoInteractions(createUserUseCase);
    }

    @Test
    @DisplayName("Vai retornar o usuário mockado")
    void testeBuscaUsuarioPeloId() throws Exception {
        User user = User.builder().id(1L).name("Guilherme").build();
        when(getUserByIdUseCase.execute(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Guilherme"));
    }

    @Test
    @DisplayName("Vai retornar 404 porque o usuario não existe")
    void testeBuscaUsuarioPeloIdNaoEncontrado() throws Exception {
        when(getUserByIdUseCase.execute(99L))
                .thenThrow(new UserNotFoundException(99L));

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found with id: 99"));
    }

    @Test
    @DisplayName("Vai retornar uma lista de usuários")
    void testeBuscaUsuarios() throws Exception {
        List<User> users = List.of(
                User.builder().id(1L).name("Guilherme").build(),
                User.builder().id(2L).name("Malu").build()
        );

        when(getAllUsersUseCase.execute()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Guilherme"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Malu"));
    }

    @Test
    @DisplayName("Vai retornar uma lista vazia pois não existem usuários")
    void testeBuscaUsuariosListaVazia() throws Exception {
        when(getAllUsersUseCase.execute()).thenReturn(List.of());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Vai retornar 200 porque atualizou o usuário")
    void testeAtualizaUsuario() throws Exception {
        User updated = User.builder().id(1L).name("Guilherme Taliberti").build();
        when(updateUserUseCase.execute(eq(1L), eq("Guilherme Taliberti"))).thenReturn(updated);

        String body = JsonBodyBuilder.toJson("Guilherme Taliberti");

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Guilherme Taliberti"));
    }

    @Test
    @DisplayName("Vai retornar 404 porque o usuário não existe")
    void testeAtualizaUsuarioInexistente() throws Exception {
        when(updateUserUseCase.execute(eq(99L), any())).thenThrow(new UserNotFoundException(99L));

        String body = JsonBodyBuilder.toJson("Guilherme Taliberti");

        mockMvc.perform(put("/users/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found with id: 99"));
    }

    @Test
    @DisplayName("Vai retornar 400 porque o nome informado está em branco")
    void testeAtualizaUsuarioNomeEmBranco() throws Exception {
        String body = JsonBodyBuilder.toJson("");

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"));

        verifyNoInteractions(updateUserUseCase);
    }

    @Test
    @DisplayName("Vai retornar 204 porque deletou um usuário")
    void testeDeletaUsuario() throws Exception {
        doNothing().when(deleteUserUseCase).execute(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());

        verify(deleteUserUseCase).execute(1L);
    }

    @Test
    @DisplayName("Vai retornar 404 porque o usuário não foi encontrado")
    void testeDeletaUsuarioNaoExistente() throws Exception {
        doThrow(new UserNotFoundException(99L))
                .when(deleteUserUseCase).execute(99L);

        mockMvc.perform(delete("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found with id: 99"));
    }
}