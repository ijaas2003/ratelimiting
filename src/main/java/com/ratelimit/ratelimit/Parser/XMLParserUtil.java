package com.ratelimit.ratelimit.Parser;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ratelimit.ratelimit.Model.Url;
import com.ratelimit.ratelimit.Model.UrlWrapper;
import com.ratelimit.ratelimit.Store.Store;
import com.ratelimit.ratelimit.Redis.*;

@Component
public class XMLParserUtil {

  @Autowired
  public Store store;

  @Autowired
  public RedisService redisService;

  public XMLParserUtil xmParserUtil;

  /**
   * Stores all URLs from the XML wrapper into the in-memory store.
   *
   * @param urlWrapper wrapper containing the list of URL configs
   * @return true if stored successfully
   */
  public boolean storeURLInStore(UrlWrapper urlWrapper) {
    List<Url> urls = urlWrapper.getUrlList();
    urls.stream().forEach(m -> store.store.put(String.format("%s&%s", m.api, m.id), m));
    System.out.println(store.store);
    return true;
  }

  /**
   * Validates whether the given API URL matches any pattern stored from XML.
   * If matched, rate limit is updated.
   *
   * @param apiUrl    the incoming API URL
   * @param apiMethod the HTTP method (currently unused)
   * @return true if valid or updated, false if no match
   */
  public boolean validateUrl(String apiUrl, String apiMethod) {
    // USER URL : url + method
    Map<String, Object> storage = store.getStore();
    String[] currentApiUrlParse = apiUrl.split("/");
    Url urlPojo = (Url) validateUrl(storage, currentApiUrlParse);
    if (urlPojo != null) {
      return redisService.updateAPIRateLimit(urlPojo);
    }
    return true;
  }

  /**
   * Matches a single URL segment against either literal text or wildcard pattern.
   *
   * @param currentApi the segment from the user API
   * @param xmlPattern the segment from the XML pattern
   * @return true if matched
   */
  private boolean matchPattern(String currentApi, String xmlPattern) {
    String xmlData = store.getDefaultXMLWildCardParams().getOrDefault(xmlPattern, "-1");
    Pattern pattern = Pattern.compile(xmlData);
    Matcher matcher = pattern.matcher(currentApi);
    return matcher.matches();
  }

  /**
   * Checks if a URL segment matches literal or wildcard.
   */
  private boolean matchUrl(String currentApiUrlParse, String urls) {
    return currentApiUrlParse.equals(urls) || matchPattern(currentApiUrlParse, urls);
  }

  /**
   * Attempts to validate the incoming URL against all stored patterns.
   *
   * @param storage            map of stored URLs
   * @param currentApiUrlParse parsed incoming URL segments
   * @return Url object if matched, else null
   */
  private Object validateUrl(Map<String, Object> storage, String[] currentApiUrlParse) {
    for (String url : storage.keySet()) {
      String[] urls = url.split("&");
      String[] urlMap = urls[0].split("/");
      if (urlMap.length != currentApiUrlParse.length) {
        continue;
      }
      if (check(currentApiUrlParse, urlMap)) {
        return storage.get(url);
      }
    }
    return null;
  }

  /**
   * Compares two URL arrays and checks whether each segment matches.
   */
  private boolean check(String[] currentApiUrlParse, String[] urlMap) {
    for (int i = 0; i < currentApiUrlParse.length; i++) {
      if (!matchUrl(currentApiUrlParse[i], urlMap[i])) {
        return false;
      } else {
        continue;
      }
    }
    return true;
  }
}
