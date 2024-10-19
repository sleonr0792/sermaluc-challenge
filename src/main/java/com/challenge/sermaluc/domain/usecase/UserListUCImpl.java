package com.challenge.sermaluc.domain.usecase;

import com.challenge.sermaluc.adapter.controller.model.Phones;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserListUCImpl implements  UserListUC{
    private final UserService userService;


    private  List<Phones> getPhoneUsersList( User user){
        return user
                .getPhoneList()
                .stream()
                .map( phone -> Phones.builder()
                    .number(phone.getNumber())
                    .countryCode(phone.getCountryCode())
                    .cityCode(phone.getCityCode())
                    .build())
                .collect(Collectors.toList());
    }
    @Override
    public List<UserDTO> listAll() {
        List<User>  users =  userService.listAll();
        return users.stream().map(
                user ->  UserDTO.builder()
                        .id(user.getUserId())
                        .email(user.getUsername())
                        .name(user.getName())
                        .token(user.getToken())
                        .registered(user.getCreated())
                        .updated(user.getModified())
                        .lastLogin(user.getLastLogin())
                        .state(user.getStatus().name())
                        .phones( getPhoneUsersList(user))
                        .build())
                .collect(Collectors.toList());
    }
}
