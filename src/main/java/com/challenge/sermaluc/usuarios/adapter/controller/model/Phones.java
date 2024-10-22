package com.challenge.sermaluc.usuarios.adapter.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Estructura para la infomación de telefonos")

public class Phones {

    @Schema(description = "numero de telefono", example = "999999999")
    private String number;

    @Schema(description = "codigo de ciudad", example = "044")
    private String cityCode;

    @Schema(description = "codigo de país", example = "+51")
    private String countryCode;

    public Phones(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }
}
