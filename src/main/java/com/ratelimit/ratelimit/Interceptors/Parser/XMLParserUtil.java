package com.ratelimit.ratelimit.Interceptors.Parser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ratelimit.ratelimit.Model.Url;
import com.ratelimit.ratelimit.Model.UrlWrapper;
import com.ratelimit.ratelimit.Store.Store;

@Component
public class XMLParserUtil {

  @Autowired
  public Store store;

  public boolean storeURLInStore(UrlWrapper urlWrapper) {
    List<Url> urls = urlWrapper.getUrlList();
    urls.stream().forEach(m -> store.store.put(m.api, m.method));
    System.out.println(store.store);
    return true;
  }
}
