package com.sdk.transcoding.service;

import com.sdk.transcoding.dto.api.ApiTranscodingResponse;
import java.net.URI;

public interface CallbackService {
  void send(URI url, ApiTranscodingResponse response);
}
