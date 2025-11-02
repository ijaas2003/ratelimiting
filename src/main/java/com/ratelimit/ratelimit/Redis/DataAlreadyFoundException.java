package com.ratelimit.ratelimit.Redis;

public class DataAlreadyFoundException extends RuntimeException {
  protected String errorCode;

  public DataAlreadyFoundException(String message) {
    super(message);
  }

  public DataAlreadyFoundException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
