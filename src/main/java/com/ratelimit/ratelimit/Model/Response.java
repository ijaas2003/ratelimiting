package com.ratelimit.ratelimit.Model;

import org.json.JSONObject;

public class Response {
  public static enum Type {
    SUCCESS,
    ERROR
  }

  public static JSONObject getResponse(String message, Type type) {
    JSONObject json = new JSONObject();
    JSONObject res = new JSONObject();
    JSONObject mess = new JSONObject();
    mess.put("message", message);
    res.put(type.toString(), mess);
    json.put("Response", res);
    return json;
  }
}
