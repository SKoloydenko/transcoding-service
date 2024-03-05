package com.sdk.transcoding.service.impl;

import com.sdk.transcoding.dto.api.ApiTranscodingResponse;
import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.dto.transcoding.TranscodingResponse;
import com.sdk.transcoding.exception.JobNotFoundException;
import com.sdk.transcoding.exception.TranscodingException;
import com.sdk.transcoding.service.CallbackService;
import com.sdk.transcoding.service.TranscodingService;
import com.sdk.transcoding.service.TranscodingWrapperService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.schild.jave.EncoderException;

@Service
@RequiredArgsConstructor
public class TranscodingServiceImpl implements TranscodingService {

  private final TranscodingWrapperService transcodingWrapperService;
  private final CallbackService callbackService;

  @Override
  public void transcode(TranscodingRequest request)
      throws EncoderException, JobNotFoundException, IOException, TranscodingException {
    TranscodingResponse response = transcodingWrapperService.encode(request);
    callbackService.send(request.callbackURL(), new ApiTranscodingResponse(response, null));
  }
}
