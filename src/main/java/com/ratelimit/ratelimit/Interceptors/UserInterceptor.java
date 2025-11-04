package com.ratelimit.ratelimit.Interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ratelimit.ratelimit.ThreadLocal.*;

public class UserInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
    String s = request.getCookies().toString();
    UserThread.setUserId(s);
    return true;
  }
}
