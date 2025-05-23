package com.ratelimiter.ratelimiter.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ratelimiter.ratelimiter.config.RateLimiterConfigProvider;
import com.ratelimiter.ratelimiter.exception.RateLimitExceededException;
import com.ratelimiter.ratelimiter.model.RateLimitKey;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RateLimiterService {

    private final Cache<RateLimitKey, AtomicInteger> requestCounts;
    private final Cache<RateLimitKey, Long> windowStartTimes;

    private final RateLimiterConfigProvider configProvider;

    public RateLimiterService(RateLimiterConfigProvider configProvider) {
        this.configProvider = configProvider;
        this.requestCounts = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();
        this.windowStartTimes = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();
    }

    public void validateRequest(String userId, String apiPath, int limit, int windowInSeconds) {
        RateLimitKey key = new RateLimitKey(userId, apiPath);
        long now = Instant.now().getEpochSecond();

        Long windowStart = windowStartTimes.getIfPresent(key);
        if (windowStart == null || now - windowStart >= windowInSeconds) {
            windowStartTimes.put(key, now);
            requestCounts.put(key, new AtomicInteger(1));
        } else {
            AtomicInteger currentCount = requestCounts.getIfPresent(key);
            if (currentCount != null && currentCount.incrementAndGet() > limit) {
                throw new RateLimitExceededException("Rate limit exceeded for user: " + userId);
            }
        }
    }

    public void validateWithDefaults(String userId, String apiPath) {
        int defaultLimit = configProvider.getDefaultLimit();
        int defaultWindow = configProvider.getDefaultWindow();
        validateRequest(userId, apiPath, defaultLimit, defaultWindow);
    }
}