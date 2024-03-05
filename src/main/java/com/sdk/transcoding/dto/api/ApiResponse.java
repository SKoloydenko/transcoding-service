package com.sdk.transcoding.dto.api;

public record ApiResponse(String message, ApiError error) {
  public ApiResponse(String message) {
    this(message, null);
  }
}
