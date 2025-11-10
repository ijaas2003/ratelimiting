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

/**
 * Interceptor responsible for validating the user identity from cookies.
 * Stores user information in a ThreadLocal for downstream components.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

  /**
   * Extracts user information from cookies. If absent, the request is blocked.
   *
   * @param request  Incoming HTTP request.
   * @param response HTTP response used to return unauthorized errors.
   * @param object   Handler object mapped for this request.
   * @return true if user is authenticated; false otherwise.
   */
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

    UserThread.setUserId(cookies[0].getName());
    return true;
  }

  /**
   * Clears the ThreadLocal user ID after the request completes.
   *
   * @param request      HTTP request.
   * @param response     HTTP response.
   * @param object       Handler object.
   * @param modelAndView The model and view (nullable).
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
      @Nullable ModelAndView modelAndView) {
    UserThread.clearUserId();
  }
}
