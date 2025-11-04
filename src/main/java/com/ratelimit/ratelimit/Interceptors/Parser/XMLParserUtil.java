package com.ratelimit.ratelimit.Interceptors.Parser;

import java.util.List;
import java.util.Map;

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

  public boolean storeURLInStore(UrlWrapper urlWrapper) {
    List<Url> urls = urlWrapper.getUrlList();
    urls.stream().forEach(m -> store.store.put(String.format("%s&%s", m.api, m.id), m));
    System.out.println(store.store);
    return true;
  }

  public boolean validateUrl(String apiUrl, String apiMethod) {
    // USER URL : url + method
    Map<String, Object> storage = store.getStore();
    String[] currentApiUrlParse = apiUrl.split("/");
    Url urlPojo = (Url) validateUrl(storage, currentApiUrlParse);
    if (urlPojo != null) {
      redisService.updateAPIRateLimit(urlPojo);
    }
    return true;
  }

  private boolean matchPattern(String currentApi) {
    String xmlData = store.getDefaultXMLWildCardParams().getOrDefault(currentApi, "-1");
    return xmlData != "-1";
  }

  private boolean matchUrl(String currentApiUrlParse, String urls) {
    return currentApiUrlParse.equals(urls) || matchPattern(currentApiUrlParse);
  }

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
