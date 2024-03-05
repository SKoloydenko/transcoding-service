package com.sdk.transcoding.mapper;

import com.sdk.transcoding.constant.enumeration.VideoResolution;
import com.sdk.transcoding.dto.api.ApiRequest;
import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.dto.transcoding.TranscodingResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TranscodingMapper {
  public TranscodingRequest asRequest(ApiRequest request, VideoResolution resolution) {
    return TranscodingRequest.builder()
        .id(UUID.randomUUID())
        .externalId(request.externalId())
        .sourcePath(request.sourcePath())
        .format(request.targetFormat())
        .resolution(resolution)
        .build();
  }

  public TranscodingResponse asResponse(
      UUID externalId, String targetPath, VideoResolution resolution) {
    return TranscodingResponse.builder()
        .externalId(externalId)
        .targetPath(targetPath)
        .resolution(resolution)
        .build();
  }
}
