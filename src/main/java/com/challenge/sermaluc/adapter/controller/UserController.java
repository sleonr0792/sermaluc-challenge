package com.challenge.sermaluc.adapter.controller;

import com.challenge.sermaluc.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;
import com.challenge.sermaluc.domain.usecase.UserCreateUC;
import com.challenge.sermaluc.domain.usecase.UserListUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de Usuarios  para la peticiones de creación y listado.
 *
 */
@RestController
@RequestMapping("${application.client.api.path}" + "/users")
@Slf4j
@Tag(name="users")
@RequiredArgsConstructor
public class UserController {

    private final UserCreateUC userCreateUC;
    private final UserListUC userListUC;

    /***
     *  Es el controlador donde se le pasa la información del requests para ser validada y posteriormente
     *  guardar el nuevo usuario.
     *  En caso de alguna validación no cumpla se lanza un excepcion con el mensaje correspondiente al error.
     *
     * @param userInfo
     * @return UserDTO
     */
    @Operation(
            summary = "Crear usuarios",
            description = "Controlador para registrar usuarios",
            tags = {"users"}
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "operacion de registro completado"),
            @ApiResponse(responseCode = "400", description = "Fallo en el registro", content = @Content(schema = @Schema(implementation = Object.class)))

    })
    @PostMapping(value = "",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO registerUser(@RequestBody UserInfo userInfo) {
        log.info("Registro de Usuario: {}", userInfo.getEmail());
        return userCreateUC.register(userInfo);
    }


    /***qwe
     * Controlador para listar un usuario por correo.
     * @return UserDTO
     */
    @Operation(
            summary = "Listar usuario",
            description = "Controlador para listar usuario",
            tags = {"users"}
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "busqueda de usuario por email"),
    })
    @GetMapping(value = "/list/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO listUsers(@PathVariable String email) {
        return userListUC.listUserByEmail(email);
    }

}
