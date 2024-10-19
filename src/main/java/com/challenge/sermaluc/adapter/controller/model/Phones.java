package com.challenge.sermaluc.adapter.controller.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class Phones {

    private String number;
    private String cityCode;
    private String countryCode;

}
