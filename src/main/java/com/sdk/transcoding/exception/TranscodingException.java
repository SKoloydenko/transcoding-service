package com.sdk.transcoding.exception;

import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.net.URI;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TranscodingException extends Exception {

  private UUID externalId;
  private VideoResolution resolution;
  private URI callbackURL;

  public TranscodingException(
      String message, UUID externalId, VideoResolution resolution, URI callbackURL) {
    super(message);
    this.externalId = externalId;
    this.resolution = resolution;
    this.callbackURL = callbackURL;
  }
}
