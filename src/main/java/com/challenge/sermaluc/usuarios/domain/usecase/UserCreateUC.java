package com.challenge.sermaluc.usuarios.domain.usecase;

import com.challenge.sermaluc.usuarios.adapter.web.dto.input.UserRegistrationRequest;
import com.challenge.sermaluc.usuarios.adapter.web.dto.output.UserResponse;

public interface UserCreateUC {

    UserResponse register(UserRegistrationRequest userRegistrationRequest);
}
