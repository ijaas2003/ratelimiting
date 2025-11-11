package com.ratelimit.ratelimit.Redis;

/**
 * Exception thrown when an operation attempts to create or insert data
 * that already exists in the system.
 * <p>
 * This is a custom runtime exception that can optionally hold an
 * error code for better error categorization.
 */
public class DataAlreadyFoundException extends RuntimeException {

  /**
   * Optional error code representing the specific failure reason.
   */
  protected String errorCode;

  /**
   * Creates an exception with a descriptive message.
   *
   * @param message the detail message explaining the error
   */
  public DataAlreadyFoundException(String message) {
    super(message);
  }

  /**
   * Creates an exception with both a message and an associated error code.
   *
   * @param message   the detail message explaining the error
   * @param errorCode application-specific code identifying the error
   */
  public DataAlreadyFoundException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
