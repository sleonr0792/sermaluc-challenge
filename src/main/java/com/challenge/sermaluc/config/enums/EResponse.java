package com.challenge.sermaluc.config.enums;

import lombok.Getter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
@ToString
public enum EResponse {


    EMAIL_INVALID(100, "Formato inválido"),
    EMAIL_EXIST(100, "Email ya existe"),
    PASS_INVALID(100, "Contraseña inválida, debe contener una Mayúscula, minúsculas y dos números");

    private final int code;
    private  final String description;
}
