package com.sdk.transcoding.service.impl;

import static com.sdk.transcoding.constant.Encoding.AUDIO_CODEC;
import static com.sdk.transcoding.constant.Encoding.CHANNELS;
import static com.sdk.transcoding.constant.Encoding.FRAME_RATE;
import static com.sdk.transcoding.constant.Encoding.SAMPLING_RATE;
import static com.sdk.transcoding.constant.Encoding.VIDEO_CODEC;

import com.sdk.transcoding.config.properties.TranscodingProperties;
import com.sdk.transcoding.constant.enumeration.JobStatus;
import com.sdk.transcoding.constant.enumeration.VideoFormat;
import com.sdk.transcoding.constant.enumeration.VideoResolution;
import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.dto.transcoding.TranscodingResponse;
import com.sdk.transcoding.exception.JobNotFoundException;
import com.sdk.transcoding.exception.TranscodingException;
import com.sdk.transcoding.listener.ProgressListener;
import com.sdk.transcoding.mapper.TranscodingMapper;
import com.sdk.transcoding.service.JobService;
import com.sdk.transcoding.service.TranscodingWrapperService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class TranscodingWrapperServiceImpl implements TranscodingWrapperService {

  private final TranscodingMapper transcodingMapper;
  @Lazy private final JobService jobService;
  private final ProgressListener progressListener;
  private final TranscodingProperties transcodingProperties;

  @Override
  public TranscodingResponse encode(TranscodingRequest request)
      throws JobNotFoundException, IOException, TranscodingException {
    progressListener.setId(request.id());

    File sourceFile = new File(request.sourcePath());
    String targetPath = getTargetPath(request.externalId(), request.resolution(), request.format());
    File targetFile = new File(targetPath);

    EncodingAttributes attributes = buildEncodingAttributes(request);

    jobService.updateStatus(request.id(), JobStatus.IN_PROGRESS);

    try {
      Encoder encoder = new Encoder();
      encoder.encode(new MultimediaObject(sourceFile), targetFile, attributes, progressListener);
      jobService.updateStatus(request.id(), JobStatus.DONE);
      jobService.updateProgress(request.id(), 100);
      return transcodingMapper.asResponse(request.externalId(), targetPath, request.resolution());
    } catch (Exception e) {
      jobService.updateStatus(request.id(), JobStatus.ERROR);
      throw new TranscodingException(
          e.getMessage(), request.externalId(), request.resolution(), request.callbackURL());
    }
  }

  private EncodingAttributes buildEncodingAttributes(TranscodingRequest request) {
    EncodingAttributes attributes = new EncodingAttributes();
    attributes.setOutputFormat(request.format().value);
    attributes.setAudioAttributes(getAudioAttributes());
    attributes.setVideoAttributes(getVideoAttributes(request.resolution()));
    return attributes;
  }

  private String getTargetPath(UUID externalId, VideoResolution resolution, VideoFormat format)
      throws IOException {
    Path dir = transcodingProperties.getRootDir().resolve(externalId.toString());
    if (!Files.exists(dir)) {
      Files.createDirectory(dir);
    }
    return dir.resolve(Path.of(String.format("%s-%s.%s", externalId, resolution, format)))
        .toString();
  }

  private AudioAttributes getAudioAttributes() {
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec(AUDIO_CODEC);
    audio.setChannels(CHANNELS);
    audio.setSamplingRate(SAMPLING_RATE);
    return audio;
  }

  private VideoAttributes getVideoAttributes(VideoResolution resolution) {
    VideoAttributes video = new VideoAttributes();
    video.setQuality(0);
    video.setCodec(VIDEO_CODEC);
    video.setFrameRate(FRAME_RATE);
    video.setSize(new VideoSize(resolution.width, resolution.height));
    return video;
  }
}
