package com.challenge.sermaluc.domain.usecase;

import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;

public interface UserListUC {
    UserDTO listUserByEmail( String email);
}
