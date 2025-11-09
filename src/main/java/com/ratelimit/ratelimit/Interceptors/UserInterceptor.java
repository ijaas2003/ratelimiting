package com.ratelimit.ratelimit.Interceptors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ratelimit.ratelimit.Model.Response;
import com.ratelimit.ratelimit.Model.ResponseConstants;
import com.ratelimit.ratelimit.Model.ResponseConstants.Type;
import com.ratelimit.ratelimit.ThreadLocal.*;

import io.micrometer.common.lang.Nullable;

@Component
public class UserInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      new Response(response)
          .setStatus(HttpStatus.UNAUTHORIZED.value())
          .setContentType(ResponseConstants.CONTENT_TYPE_JSON)
          .setType(Type.ERROR)
          .sendMessage(ResponseConstants.UNAUTHORIZED);
      return false;
    }
    UserThread.setUserId(cookies[0].getValue());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
      @Nullable ModelAndView modelAndView) {
    UserThread.clearUserId();
  }
}
