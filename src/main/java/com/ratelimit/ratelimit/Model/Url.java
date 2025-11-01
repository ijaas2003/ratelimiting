package com.ratelimit.ratelimit.Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "url")
public class Url {
  @JacksonXmlProperty(isAttribute = true)
  public String api;

  @JacksonXmlProperty(isAttribute = true)
  public String method;

  @JacksonXmlProperty(isAttribute = true)
  public String throttle;

  @JacksonXmlProperty(isAttribute = true)
  public String limit;

  @JacksonXmlProperty(isAttribute = true)
  public String block;

  @JacksonXmlProperty(isAttribute = true)
  public String windowsize;

  public Url() {

  }

  @Override
  public String toString() {
    return "ApiRule [api='" + api + "', method='" + method + "', throttle='" + throttle +
        "', limit=" + limit + ", block='" + block + "', windowsize='" + windowsize + "']";
  }

  public String getApi() {
    return api;
  }

  public void setApi(String api) {
    this.api = api;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getThrottle() {
    return throttle;
  }

  public void setThrottle(String throttle) {
    this.throttle = throttle;
  }

  public String getLimit() {
    return limit;
  }

  public void setLimit(String limit) {
    this.limit = limit;
  }

  public String getBlock() {
    return block;
  }

  public void setBlock(String block) {
    this.block = block;
  }

  public String getWindowsize() {
    return windowsize;
  }

  public void setWindowsize(String windowsize) {
    this.windowsize = windowsize;
  }
}
