package com.ratelimit.ratelimit.Interceptors.Parser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ratelimit.ratelimit.Model.Url;
import com.ratelimit.ratelimit.Model.UrlWrapper;
import com.ratelimit.ratelimit.Store.Store;

@Component
public class XMLParserUtil {

  @Autowired
  public Store store;

  public XMLParserUtil xmParserUtil;

  public boolean storeURLInStore(UrlWrapper urlWrapper) {
    List<Url> urls = urlWrapper.getUrlList();
    urls.stream().forEach(m -> store.store.put(String.format("%s&%s", m.api, m.method), m.method));
    System.out.println(store.store);
    return true;
  }

  public boolean validateUrl(String apiUrl, String apiMethod) {
    // USER URL : url + method
    Map<String, Object> storage = store.getStore();

    for (String url : storage.keySet()) {
      String[] urls = url.split("&");
      String[] urlMap = urls[0].split("/");
    }
    return true;
  }

}
