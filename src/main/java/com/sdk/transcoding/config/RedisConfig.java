package com.sdk.transcoding.config;

import static com.sdk.transcoding.constant.Topic.TRANSCODING_TOPIC;

import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
  @Value("${spring.data.redis.host}")
  private String redisHost;

  @Value("${spring.data.redis.port}")
  private String redisPort;

  @Bean
  RedissonClient redissonClient(ConsumerService consumerService) {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
    RedissonClient client = Redisson.create(config);
    RTopic topic = client.getTopic(TRANSCODING_TOPIC);
    topic.addListener(TranscodingRequest.class, consumerService);
    return client;
  }
}
