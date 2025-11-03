package com.ratelimit.ratelimit.Store;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Store {
  public Map<String, Object> store = new HashMap<>();
  public Map<String, String> xmlDefault = Map.of("{0-9}", "0", "{a-zA-Z0-9}", "0");

  public Map<String, Object> getStore() {
    return this.store;
  }

  public Map<String, String> getDefaultXMLWildCardParams() {
    return this.xmlDefault;
  }
}
