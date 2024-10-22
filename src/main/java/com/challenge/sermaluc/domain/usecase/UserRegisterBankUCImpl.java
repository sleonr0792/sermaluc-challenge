package com.challenge.sermaluc.domain.usecase;
import com.challenge.sermaluc.config.exceptions.BusinessException;
import com.challenge.sermaluc.config.jwt.JwtTokenProvider;
import com.challenge.sermaluc.domain.config.UserDomainConfig;
import com.challenge.sermaluc.domain.config.UserPasswordConfig;
import com.challenge.sermaluc.domain.model.entity.Phone;
import com.challenge.sermaluc.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.model.entity.enums.AppUserRole;
import com.challenge.sermaluc.domain.model.entity.enums.UserState;
import com.challenge.sermaluc.domain.port.UserService;
import lombok.RequiredArgsConstructor;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Setter
@Component
@RequiredArgsConstructor
public class UserRegisterBankUCImpl implements  UserCreateUC{


    private final UserService userService;

    private final UserDomainConfig userDomainConfig;
    private final UserPasswordConfig userPasswordConfig;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;




    private void throwIfEmailInvalid(final String email) {

        Pattern pattern = Pattern.compile(userDomainConfig.getPattern());
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
             throw new BusinessException(userDomainConfig.getErrorFormat());
        }
    }

    private void throwIfUserExits(final String email) {

        User user = userService.findUserByEmail(email);
        if(Objects.nonNull(user)){
            throw new BusinessException(userDomainConfig.getErrorEmailExist());
        }

    }

    private void throwIfPasswordInvalid(final String pass){
        Pattern pattern = Pattern.compile(userPasswordConfig.getPattern());
        Matcher matcher = pattern.matcher(pass);

        if(!matcher.matches()){
            throw new BusinessException(userPasswordConfig.getErrorFormat());
        }

    }

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

    @Override
    public UserDTO register(UserInfo userInfo) {

        throwIfEmailInvalid(userInfo.getEmail());
        throwIfUserExits(userInfo.getEmail());
        throwIfPasswordInvalid(userInfo.getPassword());

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


    private UserDTO buildUserDTO(User user, UserInfo userInfo){

        return UserDTO.builder()
                .id( user.getUserId())
                .email(user.getUsername())
                .name(user.getName())
                .token(user.getToken())
                .phones(userInfo.getPhones())
                .registered(user.getCreated())
                .updated(user.getModified())
                .lastLogin(user.getLastLogin())
                .state(user.getStatus().name())
                .build();
        }
}
