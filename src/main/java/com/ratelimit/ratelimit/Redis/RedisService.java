package com.ratelimit.ratelimit.Redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratelimit.ratelimit.Model.Url;
import com.ratelimit.ratelimit.Model.UrlWrapper;

@Service
public class RedisService {
  @Autowired
  private RedisAPI redisAPI;

  public boolean storeXMLInRedis(UrlWrapper urlWrapper) {
    List<Url> urls = urlWrapper.getUrlList();
    for (Url url : urls) {
      try {
        redisAPI.setZADD(url.api, "asdjai", "12345", null);
      } catch (DataAlreadyFoundException e) {
        System.out.println("Data Already Populated");
      } catch (Exception e) {
        System.out.println("Exception occur");
      }
    }
    return true;
  }

  public boolean updateAPIRateLimit() {
    return true;
  }
}
