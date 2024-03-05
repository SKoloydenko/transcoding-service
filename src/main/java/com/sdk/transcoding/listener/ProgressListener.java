package com.sdk.transcoding.listener;

import com.sdk.transcoding.exception.JobNotFoundException;
import com.sdk.transcoding.service.JobService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.progress.EncoderProgressListener;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProgressListener implements EncoderProgressListener {

  private final JobService jobService;

  UUID id;

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public void sourceInfo(MultimediaInfo multimediaInfo) {}

  @Override
  public void progress(int i) {
    try {
      jobService.updateProgress(id, i / 10);
    } catch (JobNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void message(String s) {}
}
