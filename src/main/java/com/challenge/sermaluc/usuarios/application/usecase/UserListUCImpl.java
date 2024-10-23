package com.challenge.sermaluc.usuarios.application.usecase;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Caso de Uso para listar a los usuarios registrados
 */
@Component
@RequiredArgsConstructor
public class UserListUCImpl implements  UserListUC{
    private final UserService userService;

    /**
     * Caso de uso para listar usuarios
     * @return List<UserDTO>
     */
    @Override
    public UserResponse listUserByEmail(String email) {
        return  userService.listUserByEmail(email);
    }
}
