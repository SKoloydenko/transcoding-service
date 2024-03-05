package com.sdk.transcoding.service.impl;

import static com.sdk.transcoding.constant.Topic.TRANSCODING_TOPIC;

import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

  private final RedissonClient redissonClient;

  @Override
  public void queue(TranscodingRequest request) {
    RTopic topic = redissonClient.getTopic(TRANSCODING_TOPIC);
    topic.publish(request);
  }
}
