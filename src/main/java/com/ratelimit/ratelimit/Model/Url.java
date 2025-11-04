package com.ratelimit.ratelimit.Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author Ijaas ahamad.M
 *         /**
 *         /**
 *         Represents a URL configuration for rate limiting.
 *         <p>
 *         Each instance of this class corresponds to a single URL rule in the
 *         XML
 *         configuration,
 *         defining the API endpoint, HTTP method, rate-limiting parameters, and
 *         blocking behavior.
 *         </p>
 */
@JacksonXmlRootElement(localName = "url")
public class Url {

  /** This is just unique value of {@link API} */
  @JacksonXmlProperty(isAttribute = true)
  public String id;

  /** The API endpoint path (e.g., "/api/v1/users"). */
  @JacksonXmlProperty(isAttribute = true)
  public String api;

  /** The HTTP method (e.g., GET, POST, PUT, DELETE). */
  @JacksonXmlProperty(isAttribute = true)
  public String method;

  /** The throttling strategy (e.g., "window" or "leaky-bucket"). */
  @JacksonXmlProperty(isAttribute = true)
  public String throttle;

  /** The allowed request limit within a given window size. */
  @JacksonXmlProperty(isAttribute = true)
  public String limit;

  /** The block duration or strategy when the rate limit is exceeded. */
  @JacksonXmlProperty(isAttribute = true)
  public String block;

  /** The size of the rate-limiting window (e.g., "60s" for 60 seconds). */
  @JacksonXmlProperty(isAttribute = true)
  public String windowsize;

  /** Default constructor. */
  public Url() {
  }

  /**
   * Return the API Id
   *
   * @return the Api Id
   **/
  public String getId() {
    return this.id;
  }

  /**
   * Returns the API endpoint for this rule.
   * 
   * @return the API path.
   */
  public String getApi() {
    return api;
  }

  /**
   * Sets the API endpoint path.
   * 
   * @param api the API path (e.g., "/api/v1/users").
   */
  public void setApi(String api) {
    this.api = api;
  }

  /**
   * Returns the HTTP method for this rule.
   * 
   * @return the HTTP method (e.g., "GET", "POST").
   */
  public String getMethod() {
    return method;
  }

  /**
   * Sets the HTTP method for this rule.
   * 
   * @param method the HTTP method (e.g., "GET", "POST").
   */
  public void setMethod(String method) {
    this.method = method;
  }

  /**
   * Returns the throttling strategy.
   * 
   * @return the throttling type (e.g., "window").
   */
  public String getThrottle() {
    return throttle;
  }

  /**
   * Sets the throttling strategy.
   * 
   * @param throttle the throttling type (e.g., "window").
   */
  public void setThrottle(String throttle) {
    this.throttle = throttle;
  }

  /**
   * Returns the request limit value.
   * 
   * @return the maximum allowed requests in the defined window.
   */
  public String getLimit() {
    return limit;
  }

  /**
   * Sets the request limit value.
   * 
   * @param limit the maximum allowed requests (e.g., "20").
   */
  public void setLimit(String limit) {
    this.limit = limit;
  }

  /**
   * Returns the block duration or strategy.
   * 
   * @return the block setting (e.g., "true", "5m").
   */
  public String getBlock() {
    return block;
  }

  /**
   * Sets the block duration or strategy.
   * 
   * @param block the block configuration (e.g., "true", "5m").
   */
  public void setBlock(String block) {
    this.block = block;
  }

  /**
   * Returns the window size for rate limiting.
   * 
   * @return the window size (e.g., "60s").
   */
  public String getWindowsize() {
    return windowsize;
  }

  /**
   * Sets the window size for rate limiting.
   * 
   * @param windowsize the duration of the rate-limiting window (e.g., "60s").
   */
  public void setWindowsize(String windowsize) {
    this.windowsize = windowsize;
  }

  /**
   * Returns a string representation of this rate limit URL rule.
   *
   * @return a string with all rule attributes.
   */
  @Override
  public String toString() {
    return "ApiRule [api='" + api + "', method='" + method + "', throttle='" + throttle +
        "', limit=" + limit + ", block='" + block + "', windowsize='" + windowsize + "']";
  }
}
