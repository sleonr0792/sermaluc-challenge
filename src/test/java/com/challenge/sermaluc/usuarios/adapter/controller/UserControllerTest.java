package com.challenge.sermaluc.usuarios.adapter.controller;

import com.challenge.sermaluc.usuarios.adapter.controller.model.Phones;
import com.challenge.sermaluc.usuarios.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.usuarios.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.usuarios.domain.usecase.UserCreateUC;
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
class UserControllerTest {

    @Mock
    private UserCreateUC userCreateUC;
    @InjectMocks
    private UserController userController;

    private UserInfo userInfo;

    private String username;


    @BeforeEach
    public void setUp(){

        userInfo = new UserInfo();
        username = "micorreo@domain.cl";
        userInfo.setEmail(username);
        userInfo.setName("User Test");
        userInfo.setPassword("Pass12");
        Phones phones =  new Phones(
                "123",
                "051",
                "1234566"
        );

        userInfo.setPhones(Arrays.asList(phones));

    }

    @TestFactory
    @DisplayName("When Execute Register User, Successful Process")
    List<DynamicTest> whenExecuteUserSuccessfullProcess()  {

        UserDTO usernameDTO =  new UserDTO();
        usernameDTO.setEmail(username);
        when(userCreateUC.register(any())).thenReturn(usernameDTO);

        UserDTO response = Assertions.assertDoesNotThrow(
                () -> userController.registerUser(userInfo));
        return Arrays.asList(
                DynamicTest.dynamicTest("Register user",
                        () -> Assertions.assertEquals(username, response.getEmail())),
                DynamicTest.dynamicTest("Use case process is performed one time",
                        () -> Mockito.verify(userCreateUC, times(1)).register(any()))
        );

    }

}