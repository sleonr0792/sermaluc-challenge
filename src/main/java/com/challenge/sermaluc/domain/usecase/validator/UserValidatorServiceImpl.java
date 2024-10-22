package com.challenge.sermaluc.domain.usecase.validator;

import com.challenge.sermaluc.config.exceptions.BusinessException;
import com.challenge.sermaluc.domain.config.UserDomainConfig;
import com.challenge.sermaluc.domain.config.UserPasswordConfig;
import com.challenge.sermaluc.domain.model.entity.User;
import com.challenge.sermaluc.domain.port.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Servicio para las validaciones de Negocio para los Usuarios
 */
@Service
@RequiredArgsConstructor
public class UserValidatorServiceImpl implements  UserValidatorService {

    private final UserService userService;

    private final UserDomainConfig userDomainConfig;
    private final UserPasswordConfig userPasswordConfig;

    /**
     *  Esta funcion sirve para validar el formato correcto del email
     * @param email
     */
    @Override
    public void throwIfEmailInvalid(String email) {

        Pattern pattern = Pattern.compile(userDomainConfig.getPattern());
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
            throw new BusinessException(userDomainConfig.getErrorFormat());
        }

    }

    /**
     *  esta funcion sirve para verificar si el email del usuario ha sido registrado anteriormente
     * @param email
     */
    @Override
    public void throwIfUserExits(String email) {
        User user = userService.findUserByEmail(email);
        if(Objects.nonNull(user)){
            throw new BusinessException(userDomainConfig.getErrorEmailExist());
        }
    }

    /**
     * esta funcion es para verificar que la contraseña cumpla con el patron establecido
     * @param password
     */
    @Override
    public void throwIfPasswordInvalid(String password) {
        Pattern pattern = Pattern.compile(userPasswordConfig.getPattern());
        Matcher matcher = pattern.matcher(password);

        if(!matcher.matches()){
            throw new BusinessException(userPasswordConfig.getErrorFormat());
        }

    }
}
