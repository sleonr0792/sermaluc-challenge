package com.challenge.sermaluc.usuarios.domain.validator;

public interface UserValidatorService {
    void throwIfEmailInvalid(String email);
    void throwIfUserExits(String email);
    void throwIfPasswordInvalid(String password);
}
