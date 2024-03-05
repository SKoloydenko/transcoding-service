package com.sdk.transcoding.service;

import com.sdk.transcoding.dto.transcoding.TranscodingRequest;
import com.sdk.transcoding.dto.transcoding.TranscodingResponse;
import com.sdk.transcoding.exception.JobNotFoundException;
import com.sdk.transcoding.exception.TranscodingException;
import java.io.IOException;
import ws.schild.jave.EncoderException;

public interface TranscodingWrapperService {
  TranscodingResponse encode(TranscodingRequest request)
      throws EncoderException, JobNotFoundException, IOException, TranscodingException;
}
