package com.challenge.sermaluc.usuarios.application.usercase;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.PhoneResponse;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.input.UserRegistrationRequest;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.infrastructure.exception.BusinessException;
import com.challenge.sermaluc.usuarios.infrastructure.config.security.jwt.JwtTokenProvider;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;
import com.challenge.sermaluc.usuarios.domain.model.enums.UserState;
import com.challenge.sermaluc.usuarios.application.service.UserService;
import com.challenge.sermaluc.usuarios.application.usecase.UserRegisterBankUCImpl;
import com.challenge.sermaluc.usuarios.domain.validator.UserValidatorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Uses case register user")
public class  UserRegisterBankUCImplTest {
    private UserRegisterBankUCImpl userRegisterBankUC;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserValidatorService userValidatorService;

    private User userEntity;

    private UserRegistrationRequest userRegistrationRequest;

    private String formatErrorPassword;

    @BeforeEach
    void setUp() {
        formatErrorPassword = "Contraseña inválida, debe contener una Mayúscula, minúsculas y dos números";

        PhoneResponse phoneResponse = new PhoneResponse("123","1234566","051" );
        String email = "micorreo@domain.cl";
        LocalDateTime now = LocalDateTime.now();
        userEntity = new User();
        userEntity.setUserId("uuid123");
        userEntity.setUsername(email);
        userEntity.setStatus(UserState.ACTIVE);
        userEntity.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMkBkb21haW4uY2wiL");
        userEntity.setCreated(now);
        userEntity.setModified(now);
        userEntity.setLastLogin(now);
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail(email);
        userRegistrationRequest.setName("Test user");
        userRegistrationRequest.setPassword("Pass12");
        userRegistrationRequest.setPhones(Arrays.asList(
                phoneResponse
        ));
        userRegisterBankUC = new UserRegisterBankUCImpl(
                userService,
                userValidatorService,
                passwordEncoder,
                jwtTokenProvider
        );
    }

    @Test
    @DisplayName("When Register user, then Return Data.")
    void whenUserRegister() {

        when(userService.save(any())).thenReturn(userEntity);
        UserResponse response = userRegisterBankUC.register(userRegistrationRequest);
        Assertions.assertEquals(userEntity.getUsername(), response.getEmail());
    }

    @Test
    @DisplayName("When Register user, then throws error business exception for password without format.")
    void whenUserRegisterThrowsException() {

        doThrow(new BusinessException(formatErrorPassword))
                .when(userValidatorService)
                .throwIfPasswordInvalid(any());
        userRegistrationRequest.setPassword("passwordSinformato");
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> userRegisterBankUC.register(userRegistrationRequest)
        );

        assertEquals(formatErrorPassword, exception.getMessage());
    }

}