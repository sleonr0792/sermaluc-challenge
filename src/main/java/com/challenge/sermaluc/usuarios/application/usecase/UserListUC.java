package com.challenge.sermaluc.usuarios.application.usecase;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;

public interface UserListUC {
    UserResponse listUserByEmail(String email);
}
