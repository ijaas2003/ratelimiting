package com.ratelimit.ratelimit.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.password:}")
  private String password;

  @Value("${spring.redis.timeout}")
  private int timeout;

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
