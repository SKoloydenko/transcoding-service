package com.sdk.transcoding.service.impl;

import com.sdk.transcoding.client.CallbackClient;
import com.sdk.transcoding.config.properties.BasicAuthProperties;
import com.sdk.transcoding.dto.api.ApiTranscodingResponse;
import com.sdk.transcoding.service.CallbackService;
import java.net.URI;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallbackServiceImpl implements CallbackService {

  private final CallbackClient callbackClient;
  private final BasicAuthProperties basicAuthProperties;

  @Override
  public void send(URI url, ApiTranscodingResponse response) {
    try {
      callbackClient.sendCallback(url, getAuthorizationHeader(), response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String getAuthorizationHeader() {
    String credentials =
        Base64.getEncoder()
            .encodeToString(
                String.format(
                        "%s:%s",
                        basicAuthProperties.getUsername(), basicAuthProperties.getPassword())
                    .getBytes());
    return "Basic " + credentials;
  }
}
