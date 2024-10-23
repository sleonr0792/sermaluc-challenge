package com.challenge.sermaluc.usuarios.domain.validator;

import com.challenge.sermaluc.usuarios.domain.validator.UserValidatorServiceImpl;
import com.challenge.sermaluc.usuarios.infrastructure.exception.BusinessException;
import com.challenge.sermaluc.usuarios.domain.config.UserDomainConfig;
import com.challenge.sermaluc.usuarios.domain.config.UserPasswordConfig;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;
import com.challenge.sermaluc.usuarios.domain.model.enums.UserState;
import com.challenge.sermaluc.usuarios.application.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Uses validate")
class UserValidatorServiceImplTest {
    @InjectMocks
    private UserValidatorServiceImpl userValidatorService;
    @Mock
    private UserService userService;

    private UserDomainConfig userDomainConfig;
    private UserPasswordConfig userPasswordConfig;

    private String formatErrorEmail;
    private String errorEmailExist;

    private String formatErrorPassword;
    private User user;
    private String email;

    @BeforeEach
    void setUp() {

       formatErrorEmail = "El correo debe seguir el siguiente formato: example@domain.cl";
       errorEmailExist =    "EL correo ya existe, use otro correo.";
       formatErrorPassword = "Contraseña inválida, debe contener una Mayúscula, minúsculas y dos números";

        user = new User();

        email = "micorreo@domain.cl";
        LocalDateTime now = LocalDateTime.now();
        user.setUserId("uuid123");
        user.setUsername(email);
        user.setStatus(UserState.ACTIVE);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMkBkb21haW4uY2wiL");
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        userDomainConfig = new UserDomainConfig();
        userDomainConfig.setPattern("^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$");
        userDomainConfig.setErrorEmailExist(errorEmailExist);
        userDomainConfig.setErrorFormat(formatErrorEmail);
        userPasswordConfig = new UserPasswordConfig();
        userPasswordConfig.setPattern("^(?=.*\\d){2}(?=.*[a-z])(?=.*[A-Z]).{4,}$");
        userPasswordConfig.setErrorFormat(formatErrorPassword);
        userValidatorService = new UserValidatorServiceImpl(userService,userDomainConfig,  userPasswordConfig);
    }

    @Test
    void throwIfEmailInvalid() {
        BusinessException exception = assertThrows(
                BusinessException.class,
                () ->  userValidatorService.throwIfEmailInvalid("@dominio.cl")
        );

        assertEquals(formatErrorEmail, exception.getMessage());
    }

    @Test
    void throwIfUserExits() {
        when(userService.findUserByEmail(anyString())).thenReturn(user);
        BusinessException exception = assertThrows(
                BusinessException.class,
                () ->  userValidatorService.throwIfUserExits(email)
        );

        assertEquals(errorEmailExist, exception.getMessage());
    }

    @Test
    void throwIfPasswordInvalid() {
        BusinessException exception = assertThrows(
                BusinessException.class,
                () ->  userValidatorService.throwIfPasswordInvalid("passwordSinformatoCorrecto")
        );

        assertEquals(formatErrorPassword, exception.getMessage());
    }
}