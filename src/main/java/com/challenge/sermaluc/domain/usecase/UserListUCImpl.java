package com.challenge.sermaluc.domain.usecase;

import com.challenge.sermaluc.adapter.controller.model.Phones;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public UserDTO listUserByEmail( String email) {
        return  userService.listUserByEmail(email);
    }
}
