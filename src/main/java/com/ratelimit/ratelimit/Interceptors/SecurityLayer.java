package com.ratelimit.ratelimit.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global security layer configuration that registers all application-level
 * interceptors. This ensures that incoming HTTP requests pass through
 * user validation and rate-limiting logic before reaching controllers.
 */
@Configuration
public class SecurityLayer implements WebMvcConfigurer {

  @Autowired
  private RateLimiterInterceptor rateLimiterInterceptor;

  @Autowired
  private UserInterceptor userInterceptor;

  /**
   * Registers custom interceptors to the Spring MVC registry.
   *
   * @param interceptorRegistry Registry that holds all request interceptors.
   */
  @Override
  public void addInterceptors(InterceptorRegistry interceptorRegistry) {
    interceptorRegistry.addInterceptor(userInterceptor);
    interceptorRegistry.addInterceptor(rateLimiterInterceptor);
  }
}
