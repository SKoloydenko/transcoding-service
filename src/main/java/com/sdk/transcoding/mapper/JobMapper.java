package com.sdk.transcoding.mapper;

import com.sdk.transcoding.constant.enumeration.VideoResolution;
import com.sdk.transcoding.dto.job.JobResponse;
import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
  public Job asEntity(TranscodingRequest request, VideoResolution resolution) {
    return Job.builder()
        .id(request.id())
        .externalId(request.externalId())
        .sourcePath(request.sourcePath())
        .format(request.format())
        .resolution(resolution)
        .callbackURL(request.callbackURL().toString())
        .build();
  }

  public JobResponse asResponse(Job job) {
    return JobResponse.builder()
        .id(job.getId())
        .createdAt(job.getCreatedAt())
        .externalId(job.getExternalId())
        .format(job.getFormat())
        .resolution(job.getResolution())
        .progress(job.getProgress())
        .status(job.getStatus())
        .build();
  }
}
