package com.ratelimiter.ratelimiter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RateLimiterConfigProvider {

    @Value("${rate-limiter.default-limit:100}")
    private int defaultLimit;

    @Value("${rate-limiter.default-window-seconds:60}")
    private int defaultWindow;

    public int getDefaultLimit() {
        return defaultLimit;
    }

    public int getDefaultWindow() {
        return defaultWindow;
    }
}