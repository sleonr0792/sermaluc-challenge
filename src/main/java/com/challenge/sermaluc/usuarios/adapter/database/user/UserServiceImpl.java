package com.challenge.sermaluc.usuarios.adapter.database.user;

import com.challenge.sermaluc.usuarios.adapter.controller.model.Phones;
import com.challenge.sermaluc.usuarios.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.usuarios.adapter.database.repository.UserRepository;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;
import com.challenge.sermaluc.usuarios.domain.port.UserService;
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
