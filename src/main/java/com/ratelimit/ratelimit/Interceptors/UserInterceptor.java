package com.ratelimit.ratelimit.Interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.ratelimit.ratelimit.ThreadLocal.*;

import io.micrometer.common.lang.Nullable;

@Component
public class UserInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
    String s = request.getCookies()[0].getValue();
    UserThread.setUserId(s);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
      @Nullable ModelAndView modelAndView) {
    UserThread.clearUserId();
  }
}
