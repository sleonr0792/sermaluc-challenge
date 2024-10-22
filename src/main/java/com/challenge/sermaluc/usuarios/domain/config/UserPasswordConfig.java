package com.challenge.sermaluc.usuarios.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "user.password")
@Getter
@Setter
public class UserPasswordConfig {
    private String pattern;
    private String errorFormat;
}
