package com.sdk.transcoding.exception;

import com.sdk.transcoding.dto.api.ApiError;
import com.sdk.transcoding.dto.api.ApiResponse;
import com.sdk.transcoding.dto.api.ApiTranscodingResponse;
import com.sdk.transcoding.service.CallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class TranscodingControllerAdvice {

  private final CallbackService callbackService;

  @ExceptionHandler(value = JobNotFoundException.class)
  ResponseEntity<ApiResponse> handleJobNotFoundException(JobNotFoundException exception) {
    ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new ApiResponse("Job not found", error));
  }

  @ExceptionHandler(value = TranscodingException.class)
  void handleTranscodingException(TranscodingException exception) {
    ApiError error =
        new ApiError(
            HttpStatus.BAD_REQUEST,
            exception.getMessage(),
            exception.getExternalId(),
            exception.getResolution());

    callbackService.send(exception.getCallbackURL(), new ApiTranscodingResponse(null, error));
  }
}
