package com.challenge.sermaluc.usuarios.config.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Schema(description = "clase de errores de negocio del cliente")
@Getter
public class BusinessErrorResponse {
    @Schema(description = "codigo de error", example = "400")
    private final String code;

    @Schema(description = "error de validacion de negocio en el request", example = "Contraseña inválida, debe contener una Mayúscula, minúsculas y dos números")
    private final String mensaje;
    public BusinessErrorResponse(BusinessException businessException){
        this.code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        this.mensaje = businessException.getMessage();
    }
}
