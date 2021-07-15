package com.blueparking.rest.config.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "custom")
public class TokenInfo {
    String jwtKey;
}
