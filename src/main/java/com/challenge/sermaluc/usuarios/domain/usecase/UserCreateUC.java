package com.challenge.sermaluc.usuarios.domain.usecase;

import com.challenge.sermaluc.usuarios.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.usuarios.adapter.controller.model.outbound.UserDTO;

public interface UserCreateUC {

    UserDTO register(UserInfo userInfo);
}
