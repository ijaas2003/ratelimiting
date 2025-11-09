package com.ratelimit.ratelimit.Interceptors;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ratelimit.ratelimit.Model.RateLimitConstants;
import com.ratelimit.ratelimit.Model.Response;
import com.ratelimit.ratelimit.Parser.XMLParserUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

  @Autowired
  private XMLParserUtil xmlParserUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    boolean rateLimitReached = xmlParserUtil.validateUrl(request.getRequestURI(), request.getMethod());
    if (!rateLimitReached) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
      response.setContentType("application/json");
      try {
        response.getWriter()
            .write(
                Response.getResponse(RateLimitConstants.ERROR_MESSAGE_LIMIT_REACHED, Response.Type.ERROR).toString());

      } catch (IOException e) {
        System.out.println(e);
      }
      return false;
    }
    return true;
  }
}
