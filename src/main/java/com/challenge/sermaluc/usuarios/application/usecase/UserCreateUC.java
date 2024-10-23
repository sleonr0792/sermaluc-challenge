package com.challenge.sermaluc.usuarios.application.usecase;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.input.UserRegistrationRequest;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;

public interface UserCreateUC {

    UserResponse register(UserRegistrationRequest userRegistrationRequest);
}
