package com.sdk.transcoding.controller;

import com.sdk.transcoding.dto.api.ApiRequest;
import com.sdk.transcoding.dto.api.ApiResponse;
import com.sdk.transcoding.dto.job.JobResponse;
import com.sdk.transcoding.service.JobService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TranscodingController {
  private final JobService jobService;

  @GetMapping("/{externalId}/jobs")
  ResponseEntity<List<JobResponse>> list(@PathVariable UUID externalId) {
    List<JobResponse> jobs = jobService.list(externalId);
    return ResponseEntity.ok().body(jobs);
  }

  @PostMapping("/transcode")
  ResponseEntity<ApiResponse> transcode(@RequestBody ApiRequest request) {
    jobService.create(request);
    return ResponseEntity.ok().body(new ApiResponse("Successfully uploaded to transcoding"));
  }

  @DeleteMapping("/{externalId}/jobs")
  ResponseEntity<ApiResponse> delete(@PathVariable UUID externalId) {
    jobService.delete(externalId);
    return ResponseEntity.ok().body(new ApiResponse("Deleted jobs for external id $externalId"));
  }
}
