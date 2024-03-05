package com.sdk.transcoding.service;

import com.sdk.transcoding.dto.transcoding.TranscodingRequest;

public interface ProducerService {
  void queue(TranscodingRequest request);
}
