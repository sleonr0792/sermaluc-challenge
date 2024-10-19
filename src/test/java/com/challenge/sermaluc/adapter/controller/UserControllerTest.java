package com.challenge.sermaluc.adapter.controller;

import com.challenge.sermaluc.adapter.controller.model.Phones;
import com.challenge.sermaluc.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.usecase.UserCreateUC;
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

    @TestFactory
    @DisplayName("When Execute Register User, Successful Process")
    List<DynamicTest> whenExecuteUserSuccessfullProcess()  {
        UserInfo userInfo = new UserInfo();
        String username = "micorreo@domain.cl";
        userInfo.setEmail(username);
        userInfo.setName("User Test");
        userInfo.setPassword("Pass12");
        Phones phones =  Phones.builder().build();
        phones.setCityCode("123");
        phones.setCountryCode("051");
        phones.setNumber("1234566");
        userInfo.setPhones(Arrays.asList(phones));

        when(userCreateUC.register(any())).thenReturn(UserDTO.builder()
                .email(username)
                .build());

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