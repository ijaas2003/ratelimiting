package com.ratelimit.ratelimit.Model;

public class ResponseConstants {
  public static enum Type {
    SUCCESS,
    ERROR
  }

  // ** This is the Content Type ResponseConstants
  public static final String CONTENT_TYPE_JSON = "application/json";

  // This is error Response (Messages)
  /**
   * @apiNote this is message Contstents {@link}
   **/
  public static final String ERROR_RATE_LIMIT_EXIED = "Api ratelimit exist please try again later";
  public static final String UNAUTHORIZED = "You are UNAUTHORIZED to access";

}
