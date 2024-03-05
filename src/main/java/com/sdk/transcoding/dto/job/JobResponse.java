package com.sdk.transcoding.dto.job;

import com.sdk.transcoding.constant.enumeration.JobStatus;
import com.sdk.transcoding.constant.enumeration.VideoFormat;
import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record JobResponse(
    UUID id,
    LocalDateTime createdAt,
    UUID externalId,
    VideoFormat format,
    VideoResolution resolution,
    int progress,
    JobStatus status) {}
