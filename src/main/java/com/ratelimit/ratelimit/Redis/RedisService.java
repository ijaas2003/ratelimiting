package com.ratelimit.ratelimit.Redis;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratelimit.ratelimit.Model.Url;
import com.ratelimit.ratelimit.Model.UrlWrapper;
import com.ratelimit.ratelimit.Model.RateLimitConstants;
import com.ratelimit.ratelimit.ThreadLocal.*;

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
    Pattern pattern = Pattern.compile(RateLimitConstants.WINDOW_REGEX);
    Matcher matcherWindow = pattern.matcher(urlPojo.getWindowsize());
    Long time = 1l;
    if (matcherWindow.matches()) {
      time = Long.parseLong(matcherWindow.group(1));
    }

    Matcher matcherBlock = pattern.matcher(urlPojo.getBlock());

    Long blockTime = 0l;
    if (matcherBlock.matches()) {
      blockTime = Long.parseLong(matcherBlock.group(1));
    }

    String userId = UserThread.getUserId();
    String key = userId + urlPojo.getId();
    boolean isUserBlocked = isUserBlocked(key);
    if (isUserBlocked) {
      return false;
    }
    if (RateLimitConstants.FIXED_WINDOW.equals(urlPojo.throttle)) {
      if (!handleFixedWindow(key, time)) {
        blockUser(key + RateLimitConstants.BLOCKED, blockTime);
      }
    } else if (RateLimitConstants.SLIDING_WINDOW.equals(urlPojo.getThrottle())) {
      if (!handleSlidingWindow(key, userId, urlPojo)) {
        blockUser(key + RateLimitConstants.BLOCKED, blockTime);
      }
    }
    return false;
  }

  private boolean handleFixedWindow(String key, Long time) {
    Optional<String> result = redisAPI.get(key);
    if (result.isEmpty()) {
      redisAPI.setINCR(key, time);
      return true;
    } else if (Integer.parseInt(result.get()) <= time) {
      redisAPI.setINCR(key, null);
      return true;
    }
    return false;
  }

  private boolean handleSlidingWindow(String key, String userId, Url urlPojo) {
    Optional<Long> result = redisAPI.getUsingScan(key);
    if (result.get() <= Long.parseLong(urlPojo.throttle)) {
      try {
        redisAPI.setZADD(key, userId, urlPojo.getId(), Long.parseLong(urlPojo.getLimit()));
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    return true;
  }

  private void blockUser(String key, Long block) {
    redisAPI.setINCR(key, block);
  }

  public boolean isUserBlocked(String key) {
    Optional<String> isBlocked = redisAPI.get(key + RateLimitConstants.BLOCKED);
    return isBlocked.isEmpty();
  }
}
