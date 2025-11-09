package com.ratelimit.ratelimit.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ratelimit.ratelimit.Model.Response;
import com.ratelimit.ratelimit.Model.ResponseConstants;
import com.ratelimit.ratelimit.Model.ResponseConstants.Type;
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
      new Response(response)
          .setStatus(HttpStatus.NOT_FOUND.value())
          .setType(Type.ERROR)
          .setContentType(ResponseConstants.CONTENT_TYPE_JSON)
          .sendMessage(ResponseConstants.ERROR_RATE_LIMIT_EXIED);
      return false;
    }
    return true;
  }
}
