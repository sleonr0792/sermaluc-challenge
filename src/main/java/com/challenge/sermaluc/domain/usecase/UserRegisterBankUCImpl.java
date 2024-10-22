package com.challenge.sermaluc.domain.usecase;

import com.challenge.sermaluc.config.jwt.JwtTokenProvider;
import com.challenge.sermaluc.domain.model.entity.Phone;
import com.challenge.sermaluc.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.model.entity.enums.AppUserRole;
import com.challenge.sermaluc.domain.model.entity.enums.UserState;
import com.challenge.sermaluc.domain.port.UserService;
import com.challenge.sermaluc.domain.usecase.validator.UserValidatorService;
import lombok.RequiredArgsConstructor;

import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Caso de uso para el registro de Usuarios
 */
@Setter
@Component
@RequiredArgsConstructor
public class UserRegisterBankUCImpl implements  UserCreateUC{


    private final UserService userService;
    private final UserValidatorService userValidatorService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    private List<Phone> buildPhones ( final UserInfo userInfo) {
        return userInfo.getPhones().stream().map(
                phone -> {
                    Phone p = new Phone();
                    p.setCityCode( phone.getCityCode());
                    p.setNumber( phone.getNumber());
                    p.setCountryCode( phone.getCountryCode());
                    return p;}
                ).collect(Collectors.toList());
    }

    /**
     * esta funcion sirve para registrar a un usuarios con sus respectivas validaciones.
     * @param userInfo
     * @return UserDTO
     */
    @Override
    public UserDTO register(UserInfo userInfo) {

        userValidatorService.throwIfEmailInvalid(userInfo.getEmail());
        userValidatorService.throwIfUserExits(userInfo.getEmail());
        userValidatorService.throwIfPasswordInvalid(userInfo.getPassword());

        User user =  new User();


        String token = jwtTokenProvider.createToken(
                userInfo.getEmail(),
                new ArrayList<>(Arrays.asList(AppUserRole.ROLE_CLIENT))
        );

        user.setToken(token);

        user.setUsername(userInfo.getEmail());
        user.setName(userInfo.getName());
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        user.setStatus(UserState.ACTIVE);

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setPhoneList( buildPhones(userInfo));
        return buildUserDTO( userService.save(user), userInfo);
    }

    private UserDTO buildUserDTO(User user, UserInfo userInfo) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getName(),
                user.getToken(),
                userInfo.getPhones(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getStatus()
        );
    }

}
