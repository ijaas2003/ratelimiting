package com.ratelimit.ratelimit.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Configuration class responsible for setting up the Redis connection pool
 * using the Jedis client library.
 * <p>
 * It reads the Redis connection properties (host, port, password, and timeout)
 * from the application configuration file (application.properties or
 * application.yml)
 * and initializes a {@link JedisPool} for efficient connection management.
 * </p>
 *
 * <p>
 * <b>Usage:</b> This bean can be injected anywhere in the project using
 * {@code @Autowired JedisPool jedisPool;}
 * </p>
 *
 * <p>
 * <b>Example:</b>
 * </p>
 * 
 * <pre>
 * try (Jedis jedis = jedisPool.getResource()) {
 *   jedis.set("key", "value");
 *   String result = jedis.get("key");
 * }
 * </pre>
 *
 * @author Ijaas
 */
@Configuration
public class RedisConfig {

  /** Redis host name or IP address */
  @Value("${spring.redis.host}")
  private String host;

  /** Redis port number */
  @Value("${spring.redis.port}")
  private int port;

  /** Redis authentication password (optional) */
  @Value("${spring.redis.password:}")
  private String password;

  /** Connection timeout (in milliseconds) */
  @Value("${spring.redis.timeout}")
  private int timeout;

  /**
   * Creates and configures a {@link JedisPool} bean.
   * <p>
   * The pool settings such as max idle, total connections,
   * and minimum idle connections are defined here.
   * </p>
   *
   * @return a configured {@link JedisPool} instance
   */
  @Bean
  public JedisPool jedisPool() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxIdle(10);
    config.setMaxTotal(20);
    config.setMinIdle(2);
    config.setBlockWhenExhausted(true);

    if (password == null || password.isEmpty()) {
      return new JedisPool(config, host, port);
    } else {
      return new JedisPool(config, host, port, timeout, password);
    }
  }
}
