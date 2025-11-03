package com.ratelimit.ratelimit.Interceptors;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ratelimit.ratelimit.Interceptors.Parser.XMLParserUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

  @Autowired
  private XMLParserUtil xmlParserUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    boolean isValid = xmlParserUtil.validateUrl(request.getRequestURI(), request.getMethod());
    if (!isValid) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.setContentType("application/json");
      try {
        response.getWriter().write("Access Denied");
      } catch (IOException e) {
        System.out.println("Exception Occured");
      }
    }
    return true;
  }
}
