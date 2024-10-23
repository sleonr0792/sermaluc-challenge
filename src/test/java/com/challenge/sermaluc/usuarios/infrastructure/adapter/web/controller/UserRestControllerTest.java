package com.challenge.sermaluc.usuarios.infrastructure.adapter.web.controller;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.web.UserRestController;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.PhoneResponse;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.input.UserRegistrationRequest;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.application.usecase.UserCreateUC;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Rest Controller User")
class UserRestControllerTest {

    @Mock
    private UserCreateUC userCreateUC;
    @InjectMocks
    private UserRestController userRestController;

    private UserRegistrationRequest userRegistrationRequest;

    private String username;


    @BeforeEach
    public void setUp(){

        userRegistrationRequest = new UserRegistrationRequest();
        username = "micorreo@domain.cl";
        userRegistrationRequest.setEmail(username);
        userRegistrationRequest.setName("User Test");
        userRegistrationRequest.setPassword("Pass12");
        PhoneResponse phoneResponse =  new PhoneResponse(
                "123",
                "051",
                "1234566"
        );

        userRegistrationRequest.setPhones(Arrays.asList(phoneResponse));

    }

    @TestFactory
    @DisplayName("When Execute Register User, Successful Process")
    List<DynamicTest> whenExecuteUserSuccessfullProcess()  {

        UserResponse usernameDTO =  new UserResponse();
        usernameDTO.setEmail(username);
        when(userCreateUC.register(any())).thenReturn(usernameDTO);

        UserResponse response = Assertions.assertDoesNotThrow(
                () -> userRestController.registerUser(userRegistrationRequest));
        return Arrays.asList(
                DynamicTest.dynamicTest("Register user",
                        () -> Assertions.assertEquals(username, response.getEmail())),
                DynamicTest.dynamicTest("Use case process is performed one time",
                        () -> Mockito.verify(userCreateUC, times(1)).register(any()))
        );

    }

}