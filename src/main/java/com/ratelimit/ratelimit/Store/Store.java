package com.ratelimit.ratelimit.Store;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * In-memory store used for holding application-level data and
 * default wildcard parameter mappings for XML configurations.
 * <p>
 * This acts as a lightweight storage component that other services
 * can access during request processing.
 */
@Component
public class Store {

  /**
   * General-purpose in-memory map for storing dynamic values.
   */
  public Map<String, Object> store = new HashMap<>();

  /**
   * Default wildcard expressions used when parsing XML configurations.
   * Maps placeholders to their corresponding regex or default values.
   */
  public Map<String, String> xmlDefault = Map.of(
      "{0-9}", "^\\d+$",
      "{a-zA-Z0-9}", "0");

  /**
   * Returns the application-level storage map.
   *
   * @return a mutable map used to store processed data
   */
  public Map<String, Object> getStore() {
    return this.store;
  }

  /**
   * Returns the predefined wildcard parameter mappings used for XML parsing.
   *
   * @return an immutable map of wildcard expressions and defaults
   */
  public Map<String, String> getDefaultXMLWildCardParams() {
    return this.xmlDefault;
  }
}
