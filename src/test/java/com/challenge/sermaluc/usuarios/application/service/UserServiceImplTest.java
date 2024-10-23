package com.challenge.sermaluc.usuarios.application.service;


import com.challenge.sermaluc.usuarios.infrastructure.adapter.repository.JpaUserRepository;
import com.challenge.sermaluc.usuarios.application.service.UserServiceImpl;
import com.challenge.sermaluc.usuarios.domain.model.entity.Phone;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;
import com.challenge.sermaluc.usuarios.domain.model.enums.UserState;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Uses service to UserService")
class UserServiceImplTest {
    private UserServiceImpl userService;
    @Mock
    private JpaUserRepository jpaUserRepository;

    private User user;
    private String email;
    @BeforeEach
    void setUp() {

        userService = new UserServiceImpl(jpaUserRepository);

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

        Phone p = new Phone();
        p.setNumber( "1234566");
        p.setCityCode("123");
        p.setCountryCode("051");
        user.setPhoneList(Arrays.asList(p));
    }

    @Test
    @DisplayName("When Find by username, then Return Data.")
    void whenFindByUsernameReturnData() {

        when(jpaUserRepository.findUserByUsername(anyString())).thenReturn(user);
        User userResponse = userService.findUserByEmail(email);
        Assertions.assertEquals(email, userResponse.getUsername());
    }

}