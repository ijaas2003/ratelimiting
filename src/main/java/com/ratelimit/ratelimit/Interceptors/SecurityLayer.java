package com.ratelimit.ratelimit.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityLayer implements WebMvcConfigurer {

  @Autowired
  private RateLimiterInterceptor rateLimiterInterceptor;

  @Autowired
  private UserInterceptor userInterceptor;

  @Autowired
  private CookieInterceptor cookieInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry interceptorRegistry) {
    interceptorRegistry.addInterceptor(cookieInterceptor);
    interceptorRegistry.addInterceptor(userInterceptor);
    interceptorRegistry.addInterceptor(rateLimiterInterceptor);
  }
}
