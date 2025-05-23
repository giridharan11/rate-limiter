package com.ratelimiter.ratelimiter.interceptor;

import com.ratelimiter.ratelimiter.annotation.RateLimit;
import com.ratelimiter.ratelimiter.annotation.RateLimit;
import com.ratelimiter.ratelimiter.service.RateLimiterService;
import com.ratelimiter.ratelimiter.util.UserContextExtractor;
import com.ratelimiter.ratelimiter.service.RateLimiterService;
import com.ratelimiter.ratelimiter.util.UserContextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    public RateLimitInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod method) {
            RateLimit rateLimit = method.getMethodAnnotation(RateLimit.class);
            String userId = UserContextExtractor.extractUserId(request);
            String apiPath = request.getRequestURI();

            if (rateLimit != null) {
                rateLimiterService.validateRequest(userId, apiPath, rateLimit.limit(), rateLimit.windowInSeconds());
            } else {
                rateLimiterService.validateWithDefaults(userId, apiPath);
            }
        }

        return true;
    }
}