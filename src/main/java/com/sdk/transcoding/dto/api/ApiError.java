package com.sdk.transcoding.dto.api;

import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public record ApiError(
    HttpStatus status, String message, UUID externalId, VideoResolution resolution) {
  public ApiError(HttpStatus status, String message) {
    this(status, message, null, null);
  }
}
