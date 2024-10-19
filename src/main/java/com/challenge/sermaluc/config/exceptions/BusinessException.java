package com.challenge.sermaluc.config.exceptions;

import com.challenge.sermaluc.config.enums.EResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason = "business error")
@Getter
public class BusinessException extends  RuntimeException{

    private static final long serialVErsionUID = 1553575465412356L;

    private final EResponse code;
    public BusinessException(EResponse code){
        super("El usuario que intenta realizar la petición no es válido");
        this.code = code;
    }

    public EResponse getCode(){
        return code;
    }
}
