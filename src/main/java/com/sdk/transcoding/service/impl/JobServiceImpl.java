package com.sdk.transcoding.service.impl;

import com.sdk.transcoding.constant.enumeration.JobStatus;
import com.sdk.transcoding.dto.api.ApiRequest;
import com.sdk.transcoding.dto.job.JobResponse;
import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.entity.Job;
import com.sdk.transcoding.exception.JobNotFoundException;
import com.sdk.transcoding.mapper.JobMapper;
import com.sdk.transcoding.mapper.TranscodingMapper;
import com.sdk.transcoding.repository.JobRepository;
import com.sdk.transcoding.service.JobService;
import com.sdk.transcoding.service.ProducerService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

  private final JobRepository jobRepository;
  private final JobMapper jobMapper;
  private final TranscodingMapper transcodingMapper;
  private final ProducerService producerService;

  @Override
  public List<JobResponse> list(UUID externalId) {
    List<Job> jobs = jobRepository.findByExternalId(externalId);
    return jobs.stream().map(jobMapper::asResponse).collect(Collectors.toList());
  }

  @Override
  public void create(ApiRequest request) {
    request
        .targetResolutions()
        .forEach(
            resolution -> {
              TranscodingRequest transcodingRequest =
                  transcodingMapper.asRequest(request, resolution);
              jobRepository.save(jobMapper.asEntity(transcodingRequest, resolution));
              producerService.queue(transcodingRequest);
            });
  }

  @Override
  public void updateStatus(UUID id, JobStatus status) throws JobNotFoundException {
    Job job = findEntityById(id);
    job.setStatus(status);
    jobRepository.save(job);
  }

  @Override
  public void updateProgress(UUID id, Integer progress) throws JobNotFoundException {
    Job job = findEntityById(id);
    job.setProgress(progress);
    jobRepository.save(job);
  }

  @Override
  public void delete(UUID externalId) {
    jobRepository
        .findByExternalId(externalId)
        .forEach(job -> jobRepository.deleteById(job.getId()));
  }

  private Job findEntityById(UUID id) throws JobNotFoundException {
    return jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
  }
}
