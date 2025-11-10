package com.ratelimit.ratelimit.Model;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Utility class used for building structured JSON responses for API output.
 * Supports status, content type, response type, and JSON body construction.
 */
public class Response {

  private HttpServletResponse res;
  private ResponseConstants.Type type;

  /**
   * Creates a new Response wrapper for the given HTTP response object.
   *
   * @param res HttpServletResponse used for writing output.
   */
  public Response(HttpServletResponse res) {
    this.res = res;
  }

  /**
   * Sets the HTTP status code for the response.
   *
   * @param status HTTP status code.
   * @return the current Response instance.
   */
  public Response setStatus(int status) {
    res.setStatus(status);
    return this;
  }

  /**
   * Sets the content type for the response.
   *
   * @param contentType MIME type (e.g., application/json).
   * @return the current Response instance.
   */
  public Response setContentType(String contentType) {
    res.setContentType(contentType);
    return this;
  }

  /**
   * Sets the type of response such as success or error.
   *
   * @param type Enum value representing the response category.
   * @return the current Response instance.
   */
  public Response setType(ResponseConstants.Type type) {
    this.type = type;
    return this;
  }

  /**
   * Sends a JSON-formatted message response.
   * Structure:
   * {
   * "error" or "success": {
   * "message": "..."
   * }
   * }
   *
   * @param message Actual message to be included inside the response.
   * @return the current Response instance.
   */
  public Response sendMessage(String message) {
    try {
      JSONObject json = new JSONObject();
      JSONObject inner = new JSONObject();
      inner.put("message", message);

      json.put(type.toString().toLowerCase(), inner);

      res.getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this;
  }
}
