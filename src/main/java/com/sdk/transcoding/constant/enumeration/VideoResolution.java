package com.sdk.transcoding.constant.enumeration;

public enum VideoResolution {
  SD(480, 360),
  FSD(640, 480),
  HD(1280, 720),
  FHD(1920, 1080);

  public final int width;
  public final int height;

  VideoResolution(int width, int height) {
    this.width = width;
    this.height = height;
  }
}
