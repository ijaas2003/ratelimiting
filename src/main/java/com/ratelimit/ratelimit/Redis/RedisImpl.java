package com.ratelimit.ratelimit.Redis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

@Service
public class RedisImpl implements RedisAPI {

  @Autowired
  private JedisPool jedisPool;

  @Override
  public Optional<String> get(String key) {
    try (Jedis jedis = jedisPool.getResource()) {
      if (jedis.exists(key)) {
        String value = jedis.get(key);
        return Optional.of(value);
      }
    }
    return Optional.empty();
  }

  @Override
  public void setZADD(String key, String member, String timeStampm, Long TTL) throws Exception {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.zadd(key, 12345.0, member);
      if (TTL != null) {
        jedis.expire(key, TTL);
      }
    }
  }

  @Override
  public void setINCR(final String key, final Long TTL) {
    try (Jedis jedis = jedisPool.getResource()) {
      if (jedis.exists(key)) {
        jedis.incr(key);
      } else {
        jedis.incr(key);
        jedis.expire(key, TTL);
      }
    }
  }

  @Override
  public Optional<Long> getUsingScan(final String key) {
    Long totalUsed = 0l;
    try (Jedis jedis = jedisPool.getResource()) {
      String cursor = new ScanParams().SCAN_POINTER_START;
      ScanParams param = new ScanParams().match(key).count(10000);
      do {
        ScanResult<String> result = jedis.scan(cursor, param);
        List<String> resultList = result.getResult();
        totalUsed += resultList.size();
        cursor = result.getCursor();
      } while (!cursor.equals("0"));
    }
    return Optional.of(totalUsed);
  }
}
