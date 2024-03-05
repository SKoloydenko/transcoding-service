package com.sdk.transcoding.dto.api;

import com.sdk.transcoding.dto.transcoding.TranscodingResponse;

public record ApiTranscodingResponse(TranscodingResponse data, ApiError error) {}
