package com.sdk.transcoding.dto.api;

import com.sdk.transcoding.constant.enumeration.VideoFormat;
import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.net.URI;
import java.util.Set;
import java.util.UUID;

public record ApiRequest(
    UUID externalId,
    String sourcePath,
    VideoFormat targetFormat,
    Set<VideoResolution> targetResolutions,
    URI callbackURL) {}
