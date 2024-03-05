package com.sdk.transcoding.constant;

public class Encoding {
  private Encoding() {}

  // Audio
  public static final String AUDIO_CODEC = "aac";
  public static final int CHANNELS = 2;
  public static final int SAMPLING_RATE = 44100;

  // Video
  public static final String VIDEO_CODEC = "libx264";
  public static final int FRAME_RATE = 25;
}
