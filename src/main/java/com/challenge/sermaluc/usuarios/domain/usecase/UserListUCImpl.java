package com.challenge.sermaluc.usuarios.domain.usecase;

import com.challenge.sermaluc.usuarios.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.usuarios.domain.port.UserService;
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
    public UserDTO listUserByEmail(String email) {
        return  userService.listUserByEmail(email);
    }
}
