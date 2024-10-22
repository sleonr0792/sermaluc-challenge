package com.challenge.sermaluc.usuarios.domain.port;

import com.challenge.sermaluc.usuarios.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;

public interface UserService {

    User findUserByEmail(String email);
    User save(User user);

    UserDTO listUserByEmail(String email);

}
