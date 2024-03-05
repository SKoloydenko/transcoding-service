package com.sdk.transcoding.dto.transcoding;

import com.sdk.transcoding.constant.enumeration.VideoFormat;
import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.net.URI;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TranscodingRequest(
    UUID id,
    UUID externalId,
    String sourcePath,
    VideoFormat format,
    VideoResolution resolution,
    URI callbackURL) {}
