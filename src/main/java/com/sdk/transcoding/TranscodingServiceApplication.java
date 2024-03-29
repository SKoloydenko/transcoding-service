package com.sdk.transcoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
@EnableRedisRepositories
@ImportAutoConfiguration(classes = FeignAutoConfiguration.class)
public class TranscodingServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TranscodingServiceApplication.class, args);
  }
}
