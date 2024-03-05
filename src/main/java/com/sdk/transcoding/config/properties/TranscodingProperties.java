package com.sdk.transcoding.config.properties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "properties.transcoding")
public class TranscodingProperties {
  private String rootDirPath;

  public Path getRootDir() throws IOException {
    Path path = Path.of(rootDirPath);
    if (!Files.exists(path)) {
      Files.createDirectory(path);
    }
    return path;
  }
}
