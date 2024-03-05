package com.sdk.transcoding.exception;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JobNotFoundException extends Exception {
  private final UUID id;

  public JobNotFoundException(UUID id) {
    super("Job with id " + id + " not found");
    this.id = id;
  }
}
