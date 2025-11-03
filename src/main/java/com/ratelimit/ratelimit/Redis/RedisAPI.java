package com.ratelimit.ratelimit.Redis;

import java.util.Optional;

/**
 * RedisAPI provides an abstraction layer for interacting with Redis operations
 * used in rate limiting or caching mechanisms.
 * <p>
 * This interface defines common Redis operations like fetching and storing
 * data,
 * specifically focused on working with sorted sets (ZADD) and key-based
 * lookups.
 * </p>
 * 
 * @author Ijaas
 */
public interface RedisAPI {

  /**
   * Retrieves the value associated with the given key from Redis.
   *
   * @param key the Redis key to look up
   * @return an {@link Optional} containing the value if present, or an empty
   *         Optional if the key does not exist
   */
  public Optional<?> get(String key);

  /**
   * Adds a member with a timestamp to a Redis sorted set (ZADD operation).
   * <p>
   * This is typically used for rate-limiting logic where timestamps are stored in
   * a sorted set.
   * </p>
   *
   * @param key       the Redis sorted set key
   * @param member    the member to add (e.g., user identifier, request ID)
   * @param timeStamp the timestamp to associate with the member (used as the
   *                  score)
   * @param TTL       this param stats the total time to live
   * @throws Exception if any Redis communication error occurs
   */
  public void setZADD(String key, String member, String timeStampm, Long TTL)
      throws DataAlreadyFoundException, Exception;

  /**
   * This method is used to only for COUNTERS
   *
   * @param key This key for INCR
   * @param TTL this param stats the total time to live
   **/
  public void setINCR(String key, Long TTL);
}
