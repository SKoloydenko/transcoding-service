package com.sdk.transcoding.service;

import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import org.redisson.api.listener.MessageListener;

public interface ConsumerService extends MessageListener<TranscodingRequest> {
  void handle(TranscodingRequest request);
}
