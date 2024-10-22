package com.challenge.sermaluc.usuarios.adapter.web.dto.output;

import com.challenge.sermaluc.usuarios.domain.model.entity.enums.UserState;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private  String token;
    private LocalDateTime registered;
    private LocalDateTime updated;
    private LocalDateTime lastLogin;
    private List<PhoneResponse> phones;
    private UserState state;

    public UserResponse(){

    }

    public UserResponse(String userId, String name, String email, UserState state){
        this.id = userId;
        this.name = name;
        this.email = email;
        this.state=state;
    }

    public UserResponse(String id, String name, String email, String token, List<PhoneResponse> phones, LocalDateTime registered, LocalDateTime updated, LocalDateTime lastLogin, UserState state) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.phones = phones;
        this.registered = registered;
        this.updated = updated;
        this.lastLogin = lastLogin;
        this.state = state;
    }
}
