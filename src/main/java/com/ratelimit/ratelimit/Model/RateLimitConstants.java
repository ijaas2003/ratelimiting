package com.ratelimit.ratelimit.Model;

public class RateLimitConstants {
  public static final String SLIDING_WINDOW = "slidingwindow";
  public static final String FIXED_WINDOW = "fixedwindow";

  public enum WindowTime {
    MINITUS,
    HOURS,
    SECONDS
  }
}
