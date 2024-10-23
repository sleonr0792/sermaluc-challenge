package com.challenge.sermaluc.usuarios.infrastructure.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "business error")
@Getter
public class BusinessException extends  RuntimeException{

    private static final long serialVErsionUID = 1553575465412356L;

    @Schema(description = "error de validacion de negocio en el request", example = "Contraseña inválida, debe contener una Mayúscula, minúsculas y dos números")
    private final String message;
    public BusinessException(String message){
        super("El usuarios que intenta realizar la petición no es válido");
        this.message = message;

    }

}
