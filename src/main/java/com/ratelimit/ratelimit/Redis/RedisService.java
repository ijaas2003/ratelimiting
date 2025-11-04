package com.ratelimit.ratelimit.Redis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratelimit.ratelimit.Model.Url;
import com.ratelimit.ratelimit.Model.UrlWrapper;
import com.ratelimit.ratelimit.Model.RateLimitConstants;

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

  public boolean updateAPIRateLimit(Url urlPojo) {
    String windowsize = urlPojo.windowsize;
    Pattern pattern = Pattern.compile("(\\d+)([smh]");
    Matcher matcher = pattern.matcher(urlPojo.windowsize);
    Long time = Integer.parseInt(matcher.group(1));
    if (RateLimitConstants.FIXED_WINDOW.equals(urlPojo.throttle)) {
      if (redisAPI.get(key).isPresent()) {

      }
    }
    return true;
  }
}
