package com.challenge.sermaluc.adapter.database.user;

import com.challenge.sermaluc.adapter.controller.model.Phones;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.adapter.database.repository.UserRepository;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByUsername(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDTO listUserByEmail(String email) {

        UserDTO user = userRepository.encontrarUserByEmail(email);
        List<Phones> phones = userRepository.findPhonesByUserId(user.getId());
        user.setPhones(phones);
        return user;
    }


}
