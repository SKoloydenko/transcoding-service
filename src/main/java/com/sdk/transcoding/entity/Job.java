package com.sdk.transcoding.entity;

import com.sdk.transcoding.constant.enumeration.JobStatus;
import com.sdk.transcoding.constant.enumeration.VideoFormat;
import com.sdk.transcoding.constant.enumeration.VideoResolution;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Job")
@Data
@Builder
public class Job {
  @Id private UUID id;
  private LocalDateTime createdAt;
  @Indexed private UUID externalId;
  private String sourcePath;
  private VideoFormat format;
  private VideoResolution resolution;
  private String callbackURL;
  private int progress;
  private JobStatus status;
}
