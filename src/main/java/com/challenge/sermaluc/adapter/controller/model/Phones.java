package com.challenge.sermaluc.adapter.controller.model;

import lombok.Data;

@Data
public class Phones {

    private String number;
    private String cityCode;
    private String countryCode;

    public Phones(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }
}
