package com.challenge.sermaluc.config.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason = "business error")
@Getter
public class BusinessException extends  RuntimeException{

    private static final long serialVErsionUID = 1553575465412356L;

    private final String message;
    public BusinessException(String message){
        super("El usuario que intenta realizar la petición no es válido");
        this.message = message;

    }
}
