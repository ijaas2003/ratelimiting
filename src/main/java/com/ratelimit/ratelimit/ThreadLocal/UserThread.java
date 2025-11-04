package com.ratelimit.ratelimit.ThreadLocal;

public class UserThread {
  private static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

  public static void setUserId(String userId) {
    userIdHolder.set(userId);
  }

  public static String getUserId() {
    return userIdHolder.get();
  }

  public static void clearUserId() {
    userIdHolder.remove();
  }
}
