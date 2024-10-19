package com.challenge.sermaluc.domain.usecase;

import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;

import java.util.List;

public interface UserListUC {
    List<UserDTO> listAll();
}
