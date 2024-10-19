package com.challenge.sermaluc.adapter.controller.model.inbound;

import com.challenge.sermaluc.adapter.controller.model.Phones;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserInfo {
    private String name;
    private String email;
    private String password;
    private List<Phones> phones;
}
