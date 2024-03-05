package com.sdk.transcoding.service;

import com.sdk.transcoding.constant.enumeration.JobStatus;
import com.sdk.transcoding.dto.api.ApiRequest;
import com.sdk.transcoding.dto.job.JobResponse;
import com.sdk.transcoding.exception.JobNotFoundException;
import java.util.List;
import java.util.UUID;

public interface JobService {
  List<JobResponse> list(UUID externalId);

  void create(ApiRequest request);

  void updateStatus(UUID id, JobStatus status) throws JobNotFoundException;

  void updateProgress(UUID id, Integer progress) throws JobNotFoundException;

  void delete(UUID externalId);
}
