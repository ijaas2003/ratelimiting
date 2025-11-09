package com.ratelimit.ratelimit.Model;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.http.HttpServletResponse;

public class Response {

  private HttpServletResponse res;
  private ResponseConstants.Type type;

  public Response(HttpServletResponse res) {
    this.res = res;
  }

  public Response setStatus(int status) {
    res.setStatus(status);
    return this;
  }

  public Response setContentType(String contentType) {
    res.setContentType(contentType);
    return this;
  }

  public Response setType(ResponseConstants.Type type) {
    this.type = type;
    return this;
  }

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
