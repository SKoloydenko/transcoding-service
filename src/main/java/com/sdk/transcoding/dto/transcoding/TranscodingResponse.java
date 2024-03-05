package com.sdk.transcoding.dto.transcoding;

import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TranscodingResponse(UUID externalId, String targetPath, VideoResolution resolution) {}
