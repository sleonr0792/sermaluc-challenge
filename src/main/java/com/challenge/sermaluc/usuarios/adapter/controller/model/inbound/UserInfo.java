package com.challenge.sermaluc.usuarios.adapter.controller.model.inbound;

import com.challenge.sermaluc.usuarios.adapter.controller.model.Phones;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Schema(description = "Requests para la solicitud de creacion de usuario")
public class UserInfo {
    @Schema(description = "nombre usuario", example = "example")
    private String name;

    @Schema(description = "email usuario", example = "example@dominio.cl")
    private String email;

    @Schema(description = "email usuario", example = "Abcd12")
    private String password;

    private List<Phones> phones;
}
