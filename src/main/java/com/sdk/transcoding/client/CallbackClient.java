package com.sdk.transcoding.client;

import com.sdk.transcoding.dto.api.ApiTranscodingResponse;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "transcoding", url = "callbackURL")
public interface CallbackClient {
  @PostMapping
  void sendCallback(
      URI callbackURL,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
      @RequestBody ApiTranscodingResponse response);
}
