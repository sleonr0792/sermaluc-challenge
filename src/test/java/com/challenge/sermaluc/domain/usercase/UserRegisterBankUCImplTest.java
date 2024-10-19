package com.challenge.sermaluc.domain.usercase;

import com.challenge.sermaluc.adapter.controller.model.Phones;
import com.challenge.sermaluc.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.config.jwt.JwtTokenProvider;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.model.entity.enums.UserState;
import com.challenge.sermaluc.domain.port.UserService;
import com.challenge.sermaluc.domain.usecase.UserRegisterBankUCImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
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

    @BeforeEach
    void setUp() {
        userRegisterBankUC = new UserRegisterBankUCImpl(
                userService,
                passwordEncoder,
                jwtTokenProvider
        );
        userRegisterBankUC.setRGX_DOMAIN("^(.+)@(domain.cl)$");
        userRegisterBankUC.setRGX_PASS("^(?=.*\\d){2}(?=.*[a-z])(?=.*[A-Z]).{4,}$");
    }

    @Test
    @DisplayName("When Register user, then Return Data.")
    void whenUserRegister() {
        String email = "micorreo@domain.cl";
        LocalDateTime now = LocalDateTime.now();
        User userEntity = new User();
        userEntity.setUserId("uuid123");
        userEntity.setUsername(email);
        userEntity.setStatus(UserState.ACTIVE);
        userEntity.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMkBkb21haW4uY2wiL");
        userEntity.setCreated(now);
        userEntity.setModified(now);
        userEntity.setLastLogin(now);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName("Test user");
        userInfo.setPassword("Pass12");

        userInfo.setPhones(Arrays.asList(
                Phones.builder()
                        .number("123")
                        .countryCode("1234566")
                        .cityCode("051")
                        .build()
        ));
        when(userService.save(any())).thenReturn(userEntity);
        UserDTO response = userRegisterBankUC.register(userInfo);
        Assertions.assertEquals(userEntity.getUsername(), response.getEmail());
    }


}