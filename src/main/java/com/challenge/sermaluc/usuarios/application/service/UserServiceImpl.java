package com.challenge.sermaluc.usuarios.application.service;

import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.PhoneResponse;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.infrastructure.adapter.repository.JpaUserRepository;
import com.challenge.sermaluc.usuarios.domain.model.entity.User;
import com.challenge.sermaluc.usuarios.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JpaUserRepository jpaUserRepository;
    @Override
    public User findUserByEmail(String email) {
        return jpaUserRepository.findUserByUsername(email);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public UserResponse listUserByEmail(String email) {

        UserResponse user = jpaUserRepository.encontrarUserByEmail(email);
        List<PhoneResponse> phones = jpaUserRepository.findPhonesByUserId(user.getId());
        user.setPhones(phones);
        return user;
    }


}
