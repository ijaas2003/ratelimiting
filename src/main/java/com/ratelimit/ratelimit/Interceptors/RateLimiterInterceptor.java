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

/**
 * Interceptor responsible for enforcing API rate limits
 * based on URL and HTTP method validation.
 *
 * Requests exceeding allowed limits are blocked before reaching controllers.
 */
@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

  @Autowired
  private XMLParserUtil xmlParserUtil;

  /**
   * Validates whether the incoming request exceeds the rate limit.
   *
   * @param request  Incoming HTTP request.
   * @param response HTTP response used to send error messages if blocked.
   * @param handler  Mapped handler for the request.
   * @return true if the request is allowed; false if rate limit exceeded.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    boolean isRateLimitReached = xmlParserUtil.validateUrl(request.getRequestURI(), request.getMethod());

    if (!isRateLimitReached) {
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
