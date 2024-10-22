package com.challenge.sermaluc.domain.port;

import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.model.entity.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);
    User save(User user);

    UserDTO listUserByEmail(String email);

}
