package com.challenge.sermaluc.usuarios.application.service;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User save(User user);

    UserResponse listUserByEmail(String email);

}
