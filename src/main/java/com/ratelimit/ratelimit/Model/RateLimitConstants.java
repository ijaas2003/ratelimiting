package com.ratelimit.ratelimit.Model;

public class RateLimitConstants {
  public static final String SLIDING_WINDOW = "slidingwindow";
  public static final String FIXED_WINDOW = "fixedwindow";
  public static final String BLOCKED = "_blocked";

  public enum WindowTime {
    MINITUS,
    HOURS,
    SECONDS
  }
}
