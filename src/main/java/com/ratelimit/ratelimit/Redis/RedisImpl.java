package com.ratelimit.ratelimit.Redis;

import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

/**
 * Implementation of Redis operations using Jedis.
 * Provides helper methods for basic Redis commands like GET, INCR,
 * ZADD with expiry, and scanning keys.
 */
@Service
public class RedisImpl implements RedisAPI {

  @Autowired
  private JedisPool jedisPool;

  /**
   * Fetches a value from Redis only if the key exists.
   *
   * @param key the Redis key to read
   * @return Optional containing the value if present, else empty
   */
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

  /**
   * Adds a member to a Redis Sorted Set (ZADD) with a random key suffix
   * and sets TTL if provided.
   *
   * @param key        base key used for ZSET
   * @param member     element to add into sorted set
   * @param timeStampm unused parameter (timestamp)
   * @param TTL        expiry time in minutes; if null, no expiry is set
   * @throws Exception if Redis operation fails
   */
  @Override
  public void setZADD(String key, String member, String timeStampm, Long TTL) throws Exception {
    try (Jedis jedis = jedisPool.getResource()) {
      Long random = RandomGenerator.getDefault().nextLong();
      jedis.zadd(key + random, 12345.0, member);
      if (TTL != null) {
        jedis.expire(key + random, TTL * 60);
      }
    }
  }

  /**
   * Increments a Redis key (INCR). If the key does not exist, it creates it
   * and sets the TTL.
   *
   * @param key the key to increment
   * @param TTL expiry in minutes
   */
  @Override
  public void setINCR(final String key, final Long TTL) {
    try (Jedis jedis = jedisPool.getResource()) {
      if (jedis.exists(key)) {
        jedis.incr(key);
      } else {
        jedis.incr(key);
        jedis.expire(key, TTL * 60);
      }
    }
  }

  /**
   * Scans Redis for all keys matching a given pattern and returns the total
   * count.
   *
   * @param key pattern prefix to scan for
   * @return Optional containing number of matching keys
   */
  @Override
  public Optional<Long> getUsingScan(final String key) {
    Long totalUsed = 0L;
    try (Jedis jedis = jedisPool.getResource()) {
      String cursor = new ScanParams().SCAN_POINTER_START;
      ScanParams param = new ScanParams().match(key + "*").count(10000);
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
