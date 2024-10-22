package com.challenge.sermaluc.usuarios.domain.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
    @Column(name = "user_id")
    private String userId;
}
