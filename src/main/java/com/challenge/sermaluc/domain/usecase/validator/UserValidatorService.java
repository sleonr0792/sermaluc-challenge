package com.challenge.sermaluc.domain.usecase.validator;

public interface UserValidatorService {
    void throwIfEmailInvalid(String email);
    void throwIfUserExits(String email);
    void throwIfPasswordInvalid(String password);
}
