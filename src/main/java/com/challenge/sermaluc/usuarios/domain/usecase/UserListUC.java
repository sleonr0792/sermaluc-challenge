package com.challenge.sermaluc.usuarios.domain.usecase;

import com.challenge.sermaluc.usuarios.adapter.web.dto.output.UserResponse;

public interface UserListUC {
    UserResponse listUserByEmail(String email);
}
