package com.challenge.sermaluc.usuarios.domain.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority(){
         return name();
    }
}
