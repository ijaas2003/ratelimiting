package com.ratelimit.ratelimit.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ratelimit.ratelimit.ThreadLocal.*;

public class UserInterceptor implements HandlerInterceptor {

  @Autowired
  private UserThread userThread;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
    String s = request.getCookies().toString();

    return true;
  }
}
