package com.challenge.sermaluc.adapter.database.user;


import com.challenge.sermaluc.adapter.database.repository.UserRepository;
import com.challenge.sermaluc.domain.model.entity.Phone;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.model.entity.enums.UserState;
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
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("When Find by username, then Return Data.")
    void whenFindByUsernameReturnData() {
        String email = "micorreo@domain.cl";
        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setUserId("uuid123");
        user.setUsername(email);
        user.setStatus(UserState.ACTIVE);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xhMkBkb21haW4uY2wiL");
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);

        Phone p = new Phone();
        p.setNumber("1234566");
        p.setCityCode("123");
        p.setCountryCode("051");

        user.setPhoneList(Arrays.asList(p));
        when(userRepository.findUserByUsername(anyString())).thenReturn(user);
        User userResponse = userService.findUserByEmail(email);

        Assertions.assertEquals(email, userResponse.getUsername());
    }

}