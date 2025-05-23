package com.ratelimiter.ratelimiter.model;

import java.util.Objects;

public class RateLimitKey {

    private String userId;
    private String apiPath;

    public RateLimitKey(String userId, String apiPath) {
        this.userId = userId;
        this.apiPath = apiPath;
    }

    public String getUserId() {
        return userId;
    }

    public String getApiPath() {
        return apiPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RateLimitKey)) return false;
        RateLimitKey that = (RateLimitKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(apiPath, that.apiPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, apiPath);
    }
}