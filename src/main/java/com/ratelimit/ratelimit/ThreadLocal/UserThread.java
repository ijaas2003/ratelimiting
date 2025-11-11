package com.ratelimit.ratelimit.ThreadLocal;

/**
 * Manages per-thread user ID storage using ThreadLocal.
 * <p>
 * This utility allows each request-handling thread to store and retrieve
 * a user ID without interference from other threads.
 */
public class UserThread {

  /**
   * ThreadLocal variable used to store the user ID for the current thread.
   */
  private static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

  /**
   * Sets the user ID for the current thread.
   *
   * @param userId the ID of the user to associate with this thread
   */
  public static void setUserId(String userId) {
    userIdHolder.set(userId);
  }

  /**
   * Retrieves the user ID associated with the current thread.
   *
   * @return the user ID, or null if none is set
   */
  public static String getUserId() {
    return userIdHolder.get();
  }

  /**
   * Clears the user ID stored for the current thread.
   */
  public static void clearUserId() {
    userIdHolder.remove();
  }
}
