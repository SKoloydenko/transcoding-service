package com.sdk.transcoding.service.impl;

import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.service.ConsumerService;
import com.sdk.transcoding.service.TranscodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

  private final TranscodingService transcodingService;

  @Override
  public void handle(TranscodingRequest request) {
    try {
      transcodingService.transcode(request);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onMessage(CharSequence channel, TranscodingRequest message) {
    handle(message);
  }
}
