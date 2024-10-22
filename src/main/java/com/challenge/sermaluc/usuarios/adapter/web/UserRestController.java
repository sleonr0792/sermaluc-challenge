package com.challenge.sermaluc.usuarios.adapter.web;

import com.challenge.sermaluc.usuarios.adapter.web.dto.input.UserRegistrationRequest;
import com.challenge.sermaluc.usuarios.adapter.web.dto.output.UserResponse;
import com.challenge.sermaluc.usuarios.config.exceptions.BusinessErrorResponse;
import com.challenge.sermaluc.usuarios.config.exceptions.ExceptionHandlerRestController;
import com.challenge.sermaluc.usuarios.domain.usecase.UserCreateUC;
import com.challenge.sermaluc.usuarios.domain.usecase.UserListUC;
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

/**
 * Controlador de Usuarios  para la peticiones de creación y listado.
 *
 */
@RestController
@RequestMapping("${application.client.api.path}" + "/users")
@Slf4j
@Tag(name="users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserCreateUC userCreateUC;
    private final UserListUC userListUC;

    /***
     *  Es el controlador donde se le pasa la información del requests para ser validada y posteriormente
     *  guardar el nuevo usuarios.
     *  En caso de alguna validación no cumpla se lanza un excepcion con el mensaje correspondiente al error.
     *
     * @param userRegistrationRequest
     * @return UserDTO
     */
    @Operation(
            summary = "Crear usuarios",
            description = "Controlador para registrar usuarios",
            tags = {"users"}
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "operacion de registro completado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida (Bad Request)",
                    content = @Content(schema = @Schema(implementation = BusinessErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido (Forbidden)",
                    content = @Content(schema = @Schema(implementation = ExceptionHandlerRestController.MessageError.class))),
            @ApiResponse(responseCode = "404", description = "recurso no encontrado (notfound)",
                    content = @Content(schema = @Schema(implementation = ExceptionHandlerRestController.MessageError.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ExceptionHandlerRestController.MessageError.class)))

    })
    @PostMapping(value = "",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        log.info("Registro de Usuario: {}", userRegistrationRequest.getEmail());
        return userCreateUC.register(userRegistrationRequest);
    }


    /***
     * Controlador para listar un usuarios por correo.
     * @return UserDTO
     */
    @Operation(
            summary = "Listar usuarios",
            description = "Controlador para listar usuarios",
            tags = {"users"}
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "busqueda de usuarios por email"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido (Forbidden)",
                    content = @Content(schema = @Schema(implementation = ExceptionHandlerRestController.MessageError.class))),
            @ApiResponse(responseCode = "404", description = "recurso no encontrado (notfound)",
                    content = @Content(schema = @Schema(implementation = ExceptionHandlerRestController.MessageError.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ExceptionHandlerRestController.MessageError.class)))

    })
    @GetMapping(value = "/list/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse listUsers(@PathVariable String email) {
        return userListUC.listUserByEmail(email);
    }

}
