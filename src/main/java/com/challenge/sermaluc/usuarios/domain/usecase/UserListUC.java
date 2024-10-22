package com.challenge.sermaluc.usuarios.domain.usecase;

import com.challenge.sermaluc.usuarios.adapter.controller.model.outbound.UserDTO;

public interface UserListUC {
    UserDTO listUserByEmail(String email);
}
