package com.ratelimit.ratelimit.Model;

/**
 * Holds all constant values related to rate limiting configurations,
 * including window strategies, regex patterns, and error messages.
 */
public class RateLimitConstants {

  /**
   * Represents the sliding window rate limiting algorithm.
   */
  public static final String SLIDING_WINDOW = "slidingwindow";

  /**
   * Represents the fixed window rate limiting algorithm.
   */
  public static final String FIXED_WINDOW = "fixedwindow";

  /**
   * Suffix used to mark blocked users or endpoints.
   */
  public static final String BLOCKED = "_blocked";

  /**
   * Regular expression for extracting time durations like 10s, 5m, 1h, etc.
   * Format: (number)(unit) where unit is seconds(s), minutes(m), hours(h),
   * days(d).
   */
  public static final String WINDOW_REGEX = "(\\d+)([smhd])";

  /**
   * Default error message returned when API rate limit is reached.
   */
  public static final String ERROR_MESSAGE_LIMIT_REACHED = "api limit reached";

  /**
   * Defines time units supported for rate limiting windows.
   */
  public enum WindowTime {
    MINITUS, // Represents window duration in minutes
    HOURS, // Represents window duration in hours
    SECONDS // Represents window duration in seconds
  }
}
