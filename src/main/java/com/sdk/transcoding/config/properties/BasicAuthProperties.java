package com.sdk.transcoding.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "properties.basic")
public class BasicAuthProperties {
  private String username;
  private String password;
}
