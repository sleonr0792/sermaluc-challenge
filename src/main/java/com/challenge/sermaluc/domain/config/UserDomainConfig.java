package com.challenge.sermaluc.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "user.domain")
@Getter
@Setter
public class UserDomainConfig {
    private String pattern;
    private String errorFormat;
    private String errorEmailExist;
}
